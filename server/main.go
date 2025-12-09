package main

import (
	"aayushjoshi2709.github.com/myvid/config"
	"aayushjoshi2709.github.com/myvid/route"
	"github.com/gin-gonic/gin"
)

func init() {
	config.LoadConfig()
}

func main() {
	router := gin.Default()
	route.Init(router)
	router.Run()
}
