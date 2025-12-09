package model

import "gorm.io/gorm"

type Subscription struct {
	*gorm.Model
	ChannelId uint
	UserId    uint
}
