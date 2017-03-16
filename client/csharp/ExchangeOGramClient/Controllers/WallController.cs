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

            //var call = client.GetWallPosts(new ExchangeOGram.GetWallPostsRequest
            //{
            //    Username = "someuser"
            //});

            // read all posts
            //var responses = await call.ResponseStream.ToListAsync();
            //var wallPosts = new List<WallPost>(responses.Select(r => r.Post));
            var wallPosts = new List<WallPost>
            {
                new WallPost
                {
                    Caption = "I love this app!",
                    Username = "johnsmith"
                },
                new WallPost
                {
                    Caption = "This post is now on-the-line.",
                    Username = "exchangeogram_fan",
                    MediaId = new MediaId { Id = 10 }
                }
            };

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
        public async Task<ActionResult> AddPost([Bind("Caption")] WallPost wallPost, IFormFile mediaFile)
        {
        
            if (ModelState.IsValid)
            {
                
                using (var mediaStream = mediaFile.OpenReadStream())
                {
                
                }
                

                //wallPost.Username = "someuser";
                //await clientProvider.Client.PostToWallAsync(new PostToWallRequest
                //{
                //    Post = wallPost
                //});

                return RedirectToAction("Index");
            }
            return ViewForm();
        }


        private ActionResult ViewForm()
        {
            var form = new ViewModels.Wall.Form()
            {
                WallPost = new WallPost()
            };
            return View("/Views/Wall/Form.cshtml", form);
        }
    }
}
