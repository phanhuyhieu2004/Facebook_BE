package com.example.facebook_be.dto;

public class AccountDTO {
                private String username;
                private String password;
                private String email;
                private String phone;
                private String birthday;
                private String avatar;
                private String fullName;
                private String address;
                private String interests;
                private int friendCount;
                private int commonFriendCount;
                private int role;
                private String status;

                // Getter và Setter cho các trường dữ liệu
                public String getUsername() {
                        return username;
                }

                public void setUsername(String username) {
                        this.username = username;
                }

                public String getPassword() {
                        return password;
                }

                public void setPassword(String password) {
                        this.password = password;
                }

                public String getEmail() {
                        return email;
                }

                public void setEmail(String email) {
                        this.email = email;
                }

                public String getPhone() {
                        return phone;
                }

                public void setPhone(String phone) {
                        this.phone = phone;
                }

                public String getBirthday() {
                        return birthday;
                }

                public void setBirthday(String birthday) {
                        this.birthday = birthday;
                }

                public String getAvatar() {
                        return avatar;
                }

                public void setAvatar(String avatar) {
                        this.avatar = avatar;
                }

                public String getFullName() {
                        return fullName;
                }

                public void setFullName(String fullName) {
                        this.fullName = fullName;
                }

                public String getAddress() {
                        return address;
                }

                public void setAddress(String address) {
                        this.address = address;
                }

                public String getInterests() {
                        return interests;
                }

                public void setInterests(String interests) {
                        this.interests = interests;
                }

                public int getFriendCount() {
                        return friendCount;
                }

                public void setFriendCount(int friendCount) {
                        this.friendCount = friendCount;
                }

                public int getCommonFriendCount() {
                        return commonFriendCount;
                }

                public void setCommonFriendCount(int commonFriendCount) {
                        this.commonFriendCount = commonFriendCount;
                }

                public int getRole() {
                        return role;
                }

                public void setRole(int role) {
                        this.role = role;
                }

                public String getStatus() {
                        return status;
                }

                public void setStatus(String status) {
                        this.status = status;
                }

}
