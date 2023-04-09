package schemas

import (
	"github.com/gin-gonic/gin"
	"github.com/tinadim/polyglot-rest-api/servers/go/gin/src/api/v1/lib/validation"
	"github.com/xeipuuv/gojsonschema"
)

func GetLoginSchema() gojsonschema.Schema {
	jsonSchema := gin.H{
		"type": "object",
		"properties": gin.H{
			"username": gin.H{
				"type":      "string",
				"minLength": 1,
			},
			"password": gin.H{
				"type":      "string",
				"minLength": 1,
			},
		},
		"required": [2]string{"password", "username"},
	}
	return validation.CreateBodySchema(jsonSchema)
}
