using System;
using System.IO;
using System.Reflection;
using Grpc.Core;
using System.Collections.Generic;

namespace ExchangeOGram
{
    public class ClientProvider
    {
        const string CaPemResourceName = "ExchangeOGramClient.ca.pem";
        const string BackendEnvName = "EXCHANGEOGRAM_BACKEND";

        Channel channel;
        WallService.WallServiceClient wallClient;
        MediaService.MediaServiceClient mediaClient;

        public ClientProvider()
        {
            var options = new List<ChannelOption>
            {
                new ChannelOption(ChannelOptions.SslTargetNameOverride, "demo-linux1")
            };
            this.channel = new Channel(GetBackend(), GetSslCredentials(), options);
            this.wallClient = new WallService.WallServiceClient(channel);
            this.mediaClient = new MediaService.MediaServiceClient(channel);
        }

        public WallService.WallServiceClient WallClient
        {
            get => wallClient;
        }

        public MediaService.MediaServiceClient MediaClient
        {
            get => mediaClient;
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

        private string GetBackend()
        {
           var backend = Environment.GetEnvironmentVariable(BackendEnvName);
           if (!string.IsNullOrEmpty(backend))
           {
               return backend;
           }
           return "localhost:8433";
        }
    }
}
