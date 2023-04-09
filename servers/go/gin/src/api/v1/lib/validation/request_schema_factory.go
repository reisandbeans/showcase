package validation

import (
	"github.com/gin-gonic/gin"
	"github.com/xeipuuv/gojsonschema"
)

func CreateBodySchema(bodySchema gin.H) gojsonschema.Schema {
	return createBaseSchema(bodySchema, gin.H{}, gin.H{})
}

func CreateBodySchemaWithParams(bodySchema gin.H, paramsSchema gin.H) gojsonschema.Schema {
	return createBaseSchema(bodySchema, gin.H{}, paramsSchema)
}

func CreateQuerySchema(querySchema gin.H) gojsonschema.Schema {
	return createBaseSchema(gin.H{}, querySchema, gin.H{})
}

func CreateQuerySchemaWithParams(querySchema gin.H, paramsSchema gin.H) gojsonschema.Schema {
	return createBaseSchema(gin.H{}, querySchema, paramsSchema)
}

func createBaseSchema(bodySchema gin.H, querySchema gin.H, paramsSchema gin.H) gojsonschema.Schema {
	baseSchema := gin.H{
		"type": "object",
		"properties": gin.H{
			"body":         bodySchema,
			"querySchema":  querySchema,
			"paramsSchema": paramsSchema,
		},
		"required": [3]string{"body", "query", "params"},
	}
	loader := gojsonschema.NewGoLoader(baseSchema)
	schema, _ := gojsonschema.NewSchema(loader)
	return *schema
}
