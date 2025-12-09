package model

import "gorm.io/gorm"

type Video struct {
	*gorm.Model
	videoUrl     string
	originalType string
	name         string
	views        int
	likes        int
}
