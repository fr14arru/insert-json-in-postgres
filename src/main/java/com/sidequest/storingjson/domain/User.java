package com.sidequest.storingjson.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.prefs.Preferences;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String username;
    private String password;
    private Integer age;
    private String gender;
    private Address address;
    private List<PhoneNumber> phoneNumbers;
    private Boolean isActive;
    private OffsetDateTime createdAt;
    private OffsetDateTime lastLogin;
    private List<String> roles;
    private Preferences preferences;
    private List<Order> orders;
    private List<Subscription> subscriptions;
    private List<PaymentMethod> paymentMethods;
    private SocialMedia socialMedia;
    private Work work;
    private List<FamilyMember> family;
    private List<String> hobbies;
    private List<Device> devices;
    private List<ActivityLog> activityLog;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Address implements Serializable {
        private String street;
        private String city;
        private String state;
        private String zip;
        private String country;
        private Coordinates coordinates;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Coordinates implements Serializable {
        private Double latitude;
        private Double longitude;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class PhoneNumber implements Serializable {
        private String type;
        private String number;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Preference implements Serializable {
        private Notifications notifications;
        private String theme;
        private String language;
        private String timezone;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Notifications implements Serializable {
        private Boolean email;
        private Boolean sms;
        private Boolean push;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Order implements Serializable {
        private Long orderId;
        private OffsetDateTime date;
        private Double amount;
        private String currency;
        private String status;
        private List<Item> items;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Item implements Serializable {
        private Long productId;
        private String name;
        private String category;
        private Integer quantity;
        private Double price;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Subscription implements Serializable {
        private String subscriptionId;
        private String type;
        private LocalDate startDate;
        private LocalDate endDate;
        private Boolean renewal;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class PaymentMethod implements Serializable {
        private String cardType;
        private String cardNumber;
        private String expiryDate;
        private BillingAddress billingAddress;
        private String email;
        private String linkedBank;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class BillingAddress implements Serializable {
        private String street;
        private String city;
        private String state;
        private String zip;
        private String country;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class SocialMedia implements Serializable {
        private String twitter;
        private String instagram;
        private String linkedin;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Work implements Serializable {
        private String company;
        private String position;
        private String department;
        private Double salary;
        private LocalDate hireDate;
        private List<String> skills;
        private List<Project> projects;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Project  implements Serializable{
        private String projectId;
        private String name;
        private String status;
        private LocalDate startDate;
        private LocalDate endDate;
        private List<String> technologies;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class FamilyMember implements Serializable {
        private String relation;
        private String name;
        private Integer age;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Device implements Serializable {
        private String deviceId;
        private String type;
        private String brand;
        private String model;
        private String os;
        private OffsetDateTime lastUsed;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class ActivityLog implements Serializable {
        private OffsetDateTime timestamp;
        private String event;
        private String ip;
    }
}