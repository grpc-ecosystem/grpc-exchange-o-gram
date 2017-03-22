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
        const string BackendHostEnvName = "EXCHANGEOGRAM_BACKEND_HOST";
        const int BackendPort = 8433;

        Channel channel;
        WallService.WallServiceClient wallClient;
        MediaService.MediaServiceClient mediaClient;

        public ClientProvider()
        {
            // TODO: close the channel somewhere
            this.channel = new Channel(GetBackendHost(), BackendPort, GetSslCredentials());
            this.wallClient = new WallService.WallServiceClient(channel);
            this.mediaClient = new MediaService.MediaServiceClient(channel);
        }

        public WallService.WallServiceClient WallClient
        {
            get => wallClient;
        }

        public MediaService.MediaServiceClient MediaClient {
            get => mediaClient;
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

        private string GetBackendHost()
        {
           var backendHost = Environment.GetEnvironmentVariable(BackendHostEnvName);
           if (!string.IsNullOrEmpty(backendHost))
           {
               return backendHost;
           }
           return "localhost";
        }
    }
}
