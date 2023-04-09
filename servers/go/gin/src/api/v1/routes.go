package v1

import "github.com/gin-gonic/gin"
import healthCheck "github.com/tinadim/polyglot-rest-api/servers/go/gin/src/api/v1/resources/health-check"
import users "github.com/tinadim/polyglot-rest-api/servers/go/gin/src/api/v1/resources/users"

func Mount(router gin.RouterGroup) {
	healthCheck.Mount(*router.Group("/health-check"))
	users.Mount(*router.Group("/users"))
}
