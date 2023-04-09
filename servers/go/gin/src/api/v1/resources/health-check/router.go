package health_check

import (
	"github.com/gin-gonic/gin"
)

func Mount(router gin.RouterGroup) {
	router.Any("/ping", Ping)
}
