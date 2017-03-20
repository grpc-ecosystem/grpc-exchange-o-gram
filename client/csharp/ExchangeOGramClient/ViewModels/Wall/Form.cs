using ExchangeOGram;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ExchangeOGram.ViewModels.Wall
{
    [Bind("Caption")]
    public class Form
    {
        public string Caption { get; set; }
    }
}
