using aspnet_core.Lib.Validation;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.ModelBinding.Validation;

namespace aspnet_core.Util
{
    public class NonValidatingValidator : IObjectModelValidator
    {
        public void Validate(ActionContext actionContext, ValidationStateDictionary? validationState, string prefix, object? model)
        {
            var a = actionContext.ActionDescriptor.EndpointMetadata.OfType<ValidationSchemaAttribute>().FirstOrDefault();
            if (a != null)
            {
                Console.WriteLine(a.SchemaName);
            }
        }
    }
}
