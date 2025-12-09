package v1

import (
	"github.com/gin-gonic/gin"
)

func Init(rg *gin.RouterGroup) {
	ChannelRoutes(rg.Group("/channel"))
	SubscriptionRoutes(rg.Group("/subscription"))
	UserRoutes(rg.Group("/user"))
	VideoRoutes(rg.Group("/video"))
}
