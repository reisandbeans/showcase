package server

import (
	"github.com/gin-gonic/gin"
	v1 "github.com/tinadim/polyglot-rest-api/servers/go/gin/src/api/v1"
)

func BuildApplication() *gin.Engine {
	app := gin.Default()

	apiRouter := app.Group("/api")
	v1.Mount(*apiRouter.Group("/v1"))

	return app
}
