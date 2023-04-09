package users

import (
	"github.com/gin-gonic/gin"
	"github.com/tinadim/polyglot-rest-api/servers/go/gin/src/api/v1/middleware"
	"github.com/tinadim/polyglot-rest-api/servers/go/gin/src/api/v1/resources/users/schemas"
)

func Mount(router gin.RouterGroup) {
	router.POST("/login", middleware.Validate(schemas.GetLoginSchema()), Login)
}
