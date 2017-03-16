using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Hosting;
using Grpc.Core.Utils;
using ExchangeOGram;

namespace ExchangeOGram.Controllers
{
    public class MediaController : Controller
    {
        private readonly ClientProvider clientProvider;
        
        public MediaController(ClientProvider clientProvider)
        {
            this.clientProvider = clientProvider;
        }

        public async Task<IActionResult> GetById(long mediaId)
        {
            // var client = clientProvider.MediaClient;

            // var response = await client.DownloadImageAsync(new DownloadImageRequest
            // {
            //     Id = new MediaId { Id = mediaId }
            // });

            // var bytes = response.Image.Data.ToByteArray();
            //var imageData = new byte[] {  };
            var imageData = System.IO.File.OpenRead("C:\\Users\\jtattermusch\\kitten-2.jpg");
            return File(imageData, "image/jpeg");
        }
        
    }
}
