using Microsoft.AspNetCore.Mvc.Filters;

namespace aspnet_core.Lib.Validation
{
    public class ValidateWithSchemaAttribute : ActionFilterAttribute
    {
        private string schemaName;

        public ValidateWithSchemaAttribute(string schemaName)
        {
            this.schemaName = schemaName;
        }

        public override void OnActionExecuting(ActionExecutingContext context)
        {
            Console.WriteLine(this.schemaName);
        }
    }
}
