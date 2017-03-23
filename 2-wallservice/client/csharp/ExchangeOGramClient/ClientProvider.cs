using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Threading.Tasks;
using Grpc.Core;
using ExchangeOGram;

namespace ExchangeOGram
{
    public class ClientProvider
    {
        const string CaPemResourceName = "ExchangeOGramClient.ca.pem";

        Channel channel;
        WallService.WallServiceClient wallClient;

        public ClientProvider()
        {
            // TODO: close the channel somewhere
            this.channel = new Channel("demo-linux1:8433", GetSslCredentials());
            this.wallClient = new WallService.WallServiceClient(channel);
        }

        public WallService.WallServiceClient WallClient
        {
            get => wallClient;
        }

        // TODO: move elsewhere
        public string Username
        {
            get => "testuser";
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
