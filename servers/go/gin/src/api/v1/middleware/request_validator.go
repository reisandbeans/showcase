package middleware

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/tinadim/polyglot-rest-api/servers/go/gin/src/api/v1/lib/validation"
	"github.com/xeipuuv/gojsonschema"
	"net/http"
)

func Validate(schema gojsonschema.Schema) gin.HandlerFunc {
	return func(c *gin.Context) {
		requestData := validation.GetRequestData(c)
		dataToValidate := gojsonschema.NewGoLoader(requestData)
		result, _ := schema.Validate(dataToValidate)

		if result.Valid() {
			c.Next()
		} else {
			for _, err := range result.Errors() {
				fmt.Println(err.String())
			}

			c.JSON(http.StatusBadRequest, gin.H{
				"error": true,
			})
			c.Abort()
		}
	}
}
