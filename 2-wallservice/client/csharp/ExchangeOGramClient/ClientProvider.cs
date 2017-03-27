using System.IO;
using System.Reflection;
using Grpc.Core;

namespace ExchangeOGram
{
    public class ClientProvider
    {
        const string CaPemResourceName = "ExchangeOGramClient.ca.pem";

        Channel channel;
        WallService.WallServiceClient wallClient;

        public ClientProvider()
        {
            this.channel = new Channel("demo-linux1:8433", GetSslCredentials());
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
