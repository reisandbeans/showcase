using Microsoft.AspNetCore.Mvc;

namespace aspnet_core.Api.v1.Resources.HealthCheck
{
    [ApiController]
    [ApiVersion("1")]
    [Route("v{version:apiVersion}/health-check")]
    public class HealthCheckController : ControllerBase
    {
        private readonly ILogger<HealthCheckController> _logger;

        public HealthCheckController(ILogger<HealthCheckController> logger)
        {
            _logger = logger;
        }

        [HttpGet("ping")]
        public bool Ping()
        {
            return true;
        }
    }
}
