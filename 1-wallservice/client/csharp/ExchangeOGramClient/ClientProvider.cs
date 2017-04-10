using System.IO;
using System.Reflection;
using Grpc.Core;
using System.Collections.Generic;

namespace ExchangeOGram
{
    public class ClientProvider
    {
        const string CaPemResourceName = "ExchangeOGramClient.ca.pem";

        Channel channel;
        WallService.WallServiceClient wallClient;

        public ClientProvider()
        {
            var options = new List<ChannelOption>
            {
                new ChannelOption(ChannelOptions.SslTargetNameOverride, "demo-linux1")
            };
            this.channel = new Channel("localhost:8433", GetSslCredentials(), options);
            this.wallClient = new WallService.WallServiceClient(channel);
        }

        public WallService.WallServiceClient WallClient
        {
            get => wallClient;
        }

        private SslCredentials GetSslCredentials()
        {
            // read the certificate authority from an embedded resource
            var stream = typeof(ClientProvider).GetTypeInfo().Assembly.GetManifestResourceStream(CaPemResourceName);
            using (var streamReader = new StreamReader(stream))
            {
                return new SslCredentials(streamReader.ReadToEnd());
            }
        }
    }
}
