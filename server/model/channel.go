package model

import "gorm.io/gorm"

type Channel struct {
	*gorm.Model
	ChannelName     string
	UserId          int
	SubscriptionIds []Subscription
	VideoIds        []Video
}
