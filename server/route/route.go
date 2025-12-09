package route

import (
	v1 "aayushjoshi2709.github.com/myvid/route/v1"
	"github.com/gin-gonic/gin"
)

func Init(router *gin.Engine) {
	v1.Init(router.Group("/v1"))
}
