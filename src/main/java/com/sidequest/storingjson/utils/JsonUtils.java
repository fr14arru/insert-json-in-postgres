package com.sidequest.storingjson.utils;

import com.alibaba.fastjson.JSON;
import com.sidequest.storingjson.domain.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {

    public User getUserFromJson() {

        return JSON.parseObject(getRandomJson(), User.class);
    }

    public String getRandomJson() {

        return """
                {
                  "id": 987654321,
                  "name": "Alice Johnson",
                  "email": "alice.johnson@example.com",
                  "username": "alicej_92",
                  "password": "A1b2C3d4!",
                  "age": 31,
                  "gender": "female",
                  "address": {
                    "street": "456 Elm Street",
                    "city": "Metropolis",
                    "state": "NY",
                    "zip": "10001",
                    "country": "USA",
                    "coordinates": {
                      "latitude": 40.712776,
                      "longitude": -74.005974
                    }
                  },
                  "phoneNumbers": [
                    {
                      "type": "home",
                      "number": "+1-555-9876"
                    },
                    {
                      "type": "mobile",
                      "number": "+1-555-6543"
                    },
                    {
                      "type": "work",
                      "number": "+1-555-3210"
                    }
                  ],
                  "isActive": true,
                  "createdAt": "2023-05-12T14:30:00Z",
                  "lastLogin": "2024-02-04T22:45:15Z",
                  "roles": ["USER", "ADMIN", "MODERATOR"],
                  "preferences": {
                    "notifications": {
                      "email": true,
                      "sms": false,
                      "push": true
                    },
                    "theme": "dark",
                    "language": "es-ES",
                    "timezone": "America/New_York"
                  },
                  "orders": [
                    {
                      "orderId": 56789,
                      "date": "2024-01-15T10:15:30Z",
                      "amount": 149.99,
                      "currency": "USD",
                      "status": "delivered",
                      "items": [
                        {
                          "productId": 201,
                          "name": "Bluetooth Headphones",
                          "category": "Electronics",
                          "quantity": 1,
                          "price": 99.99
                        },
                        {
                          "productId": 202,
                          "name": "USB-C Charger",
                          "category": "Accessories",
                          "quantity": 1,
                          "price": 50.00
                        }
                      ]
                    },
                    {
                      "orderId": 67890,
                      "date": "2023-12-20T16:45:00Z",
                      "amount": 29.99,
                      "currency": "USD",
                      "status": "shipped",
                      "items": [
                        {
                          "productId": 301,
                          "name": "Wireless Mouse",
                          "category": "Electronics",
                          "quantity": 1,
                          "price": 29.99
                        }
                      ]
                    }
                  ],
                  "subscriptions": [
                    {
                      "subscriptionId": "SUB-001",
                      "type": "Premium",
                      "startDate": "2023-06-01",
                      "endDate": "2024-06-01",
                      "renewal": true
                    },
                    {
                      "subscriptionId": "SUB-002",
                      "type": "Newsletter",
                      "startDate": "2022-11-10",
                      "endDate": null,
                      "renewal": false
                    }
                  ],
                  "paymentMethods": [
                    {
                      "cardType": "Visa",
                      "cardNumber": "**** **** **** 1234",
                      "expiryDate": "2026-09",
                      "billingAddress": {
                        "street": "789 Oak Avenue",
                        "city": "Gotham",
                        "state": "NJ",
                        "zip": "07001",
                        "country": "USA"
                      }
                    },
                    {
                      "cardType": "PayPal",
                      "email": "alice.paypal@example.com",
                      "linkedBank": "Bank of America"
                    }
                  ],
                  "socialMedia": {
                    "twitter": "@alicejohnson",
                    "instagram": "alice_j92",
                    "linkedin": "linkedin.com/in/alicejohnson"
                  },
                  "work": {
                    "company": "TechCorp Inc.",
                    "position": "Software Engineer",
                    "department": "Backend Development",
                    "salary": 95000.00,
                    "hireDate": "2019-08-15",
                    "skills": ["Java", "Spring Boot", "PostgreSQL", "Docker", "Kubernetes"],
                    "projects": [
                      {
                        "projectId": "PROJ-123",
                        "name": "Microservices Migration",
                        "status": "Ongoing",
                        "startDate": "2023-03-01",
                        "endDate": null,
                        "technologies": ["Java", "Spring Boot", "Kafka", "PostgreSQL"]
                      },
                      {
                        "projectId": "PROJ-456",
                        "name": "Cloud Infrastructure",
                        "status": "Completed",
                        "startDate": "2022-01-10",
                        "endDate": "2022-12-15",
                        "technologies": ["AWS", "Terraform", "Docker", "Kubernetes"]
                      }
                    ]
                  },
                  "family": [
                    {
                      "relation": "spouse",
                      "name": "Michael Johnson",
                      "age": 33
                    },
                    {
                      "relation": "child",
                      "name": "Sophie Johnson",
                      "age": 5
                    }
                  ],
                  "hobbies": ["Cycling", "Photography", "Chess", "Gaming"],
                  "devices": [
                    {
                      "deviceId": "DVC-12345",
                      "type": "Smartphone",
                      "brand": "Apple",
                      "model": "iPhone 14",
                      "os": "iOS 17",
                      "lastUsed": "2024-02-04T18:30:00Z"
                    },
                    {
                      "deviceId": "DVC-67890",
                      "type": "Laptop",
                      "brand": "Dell",
                      "model": "XPS 15",
                      "os": "Windows 11",
                      "lastUsed": "2024-02-04T21:00:00Z"
                    }
                  ],
                  "activityLog": [
                    {
                      "timestamp": "2024-02-04T22:45:15Z",
                      "event": "Logged in",
                      "ip": "192.168.1.10"
                    },
                    {
                      "timestamp": "2024-02-03T15:22:05Z",
                      "event": "Updated password",
                      "ip": "192.168.1.20"
                    },
                    {
                      "timestamp": "2024-01-30T10:10:10Z",
                      "event": "Purchased item",
                      "details": {
                        "orderId": 56789,
                        "amount": 149.99
                      }
                    }
                  ]
                }
                """;
    }
}
