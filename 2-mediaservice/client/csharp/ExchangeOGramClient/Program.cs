﻿using System.IO;
using Microsoft.AspNetCore.Hosting;
using Grpc.Core;
using Grpc.Core.Logging;

namespace ExchangeOGram
{
    public class Program
    {
        public static void Main(string[] args)
        {
            GrpcEnvironment.SetLogger(new ConsoleLogger());
            var host = new WebHostBuilder()
                .UseKestrel()
                .UseContentRoot(Directory.GetCurrentDirectory())
                .UseIISIntegration()
                .UseStartup<Startup>()
                .UseApplicationInsights()
                .Build();

            host.Run();
        }
    }
}
