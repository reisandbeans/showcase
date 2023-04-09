using aspnet_core.Lib.Validation;
using aspnet_core.Models;
using Microsoft.AspNetCore.Mvc;

namespace aspnet_core.Api.v1.Resources.Users
{
    [ApiController]
    [ApiVersion("1")]
    [Route("v{version:apiVersion}/users")]
    public class UsersController : ControllerBase
    {
        private readonly ILogger<UsersController> _logger;

        public UsersController(ILogger<UsersController> logger)
        {
            _logger = logger;
        }

        [HttpPost("login")]
        [ValidateWithSchema("UsersLogin")]
        public bool Login([FromBody] Foo foo)
        {
            return true;
        }
    }
}