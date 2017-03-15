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
        WallService.WallServiceClient client;

        public ClientProvider()
        {
            this.channel = new Channel("127.0.0.1:50051", ChannelCredentials.Insecure);
            this.client = new WallService.WallServiceClient(channel);
        }

        public WallService.WallServiceClient Client
        {
            get { return client; }
        }
    }
}
