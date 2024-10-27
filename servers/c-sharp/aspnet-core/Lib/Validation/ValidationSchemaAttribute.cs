namespace aspnet_core.Lib.Validation
{
    [AttributeUsage(AttributeTargets.Method, Inherited = false)]
    public class ValidationSchemaAttribute : Attribute
    {
        private string schemaName;

        public ValidationSchemaAttribute(string schemaName)
        {
            this.schemaName = schemaName;
        }

        public virtual string SchemaName
        {
            get { return schemaName; }
        }
    }
}
