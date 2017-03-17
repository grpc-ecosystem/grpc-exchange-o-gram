using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Grpc.Core;
using ExchangeOGram;

namespace ExchangeOGram
{
    public class ClientProvider
    {
        Channel channel;
        WallService.WallServiceClient wallClient;
        MediaService.MediaServiceClient mediaClient;

        public ClientProvider()
        {
            // TODO: close the channel somewhere
            this.channel = new Channel("localhost:8181", ChannelCredentials.Insecure);
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
    }
}
