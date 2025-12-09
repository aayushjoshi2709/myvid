package model

import "gorm.io/gorm"

type Subscription struct {
	*gorm.Model
	channelId uint
	userId    uint
}
