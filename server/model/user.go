package model

import "gorm.io/gorm"

type User struct {
	gorm.Model
	username  string
	age       uint
	firstName string
	lastName  string
}
