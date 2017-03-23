using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Hosting;
using Grpc.Core.Utils;
using Google.Protobuf;
using System.IO;

namespace ExchangeOGram.Controllers
{
    public class WallController : Controller
    {
        private readonly ClientProvider clientProvider;
        private readonly IHostingEnvironment environment;
        
        public WallController(ClientProvider clientProvider, IHostingEnvironment environment)
        {
            this.clientProvider = clientProvider;
            this.environment = environment;
        }

        public async Task<IActionResult> Index()
        {
            var client = clientProvider.WallClient;

            var call = client.GetWallPosts(new ExchangeOGram.GetWallPostsRequest
            {
                Username = clientProvider.Username
            });

            // read all posts
            var responses = await call.ResponseStream.ToListAsync();
            var wallPosts = new List<WallPost>(responses.Select(r => r.Post));

            return View(new ViewModels.Wall.Index()
            {
                Posts = wallPosts
            });
        }

        public async Task<IActionResult> AddPost()
        {
            return ViewForm();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<ActionResult> AddPost(ViewModels.Wall.Form form, IFormFile mediaFile)
        {
            if (ModelState.IsValid)
            {
                await clientProvider.WallClient.PostToWallAsync(new PostToWallRequest
                {
                    Post = new WallPost
                    {
                        Username = clientProvider.Username,
                        Caption = form.Caption
                     }
                });

                return RedirectToAction("Index");
            }
            return ViewForm();
        }

        private ActionResult ViewForm()
        {
            var form = new ViewModels.Wall.Form()
            {
                Caption = ""
            };
            return View("/Views/Wall/Form.cshtml", form);
        }
    }
}
