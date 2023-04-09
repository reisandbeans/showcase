package main

import (
	"github.com/tinadim/polyglot-rest-api/servers/go/gin/src/server"
)

func main() {
	app := server.BuildApplication()
	app.Run() // listen and serve on 0.0.0.0:8080 (for windows "localhost:8080")
}
