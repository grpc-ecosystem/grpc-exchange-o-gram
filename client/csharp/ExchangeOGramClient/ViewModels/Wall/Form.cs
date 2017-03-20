using Microsoft.AspNetCore.Mvc;

namespace ExchangeOGram.ViewModels.Wall
{
    [Bind("Caption")]
    public class Form
    {
        public string Caption { get; set; }
    }
}
