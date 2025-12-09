package model

import "gorm.io/gorm"

type User struct {
	gorm.Model
	Username        string
	Age             uint
	FirstName       string
	LastName        string
	SubscriptionIds []Subscription
}
