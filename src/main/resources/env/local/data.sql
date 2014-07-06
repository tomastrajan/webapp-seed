INSERT INTO user (username, password, active, created, modified)
VALUES ('test', '$2a$10$v0//wnvvGd3Y94F64dKbaukFmyJOBN6k/1PqakLS9xyZ.GPID5.i.', 1, NOW(), NOW());

INSERT INTO user_role (name, active, created, modified) VALUES ('ROLE_ADMIN', 1, NOW(), NOW());
INSERT INTO user_role (name, active, created, modified) VALUES ('ROLE_USER', 1, NOW(), NOW());

INSERT INTO user_roles (user_id, roles_id) VALUES (1, 1);