package model

import "gorm.io/gorm"

type Video struct {
	*gorm.Model
	VideoUrl     string
	OriginalType string
	Name         string
	Views        int
	Likes        int
	ChannelId    int
}
