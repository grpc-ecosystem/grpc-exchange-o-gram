using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace ExchangeOGram.Controllers
{
    public class MediaController : Controller
    {
        private readonly ClientProvider clientProvider;
        
        public MediaController(ClientProvider clientProvider)
        {
            this.clientProvider = clientProvider;
        }

        public async Task<IActionResult> GetById(long? id)
        {
            var client = clientProvider.MediaClient;

            var response = await client.DownloadImageAsync(new DownloadImageRequest
            {
                 Id = new MediaId { Id = id.Value }
            });

            var imageData = response.Image.Data.ToByteArray();
            return File(imageData, response.Image.Mimetype);
        }
    }
}
