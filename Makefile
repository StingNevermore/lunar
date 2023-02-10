.DEFAULT_GOAL := distribution

.PHONY: distribution
distribution: server frontend
	@echo "\033[0;32m构建完成，请查看distributions目录\033[0m"

.PHONY: server
server: server-move
.PHONY: server-build
server-build:
	./mvnw -am -pl lunar-backend/lunar-server clean package -U
.PHONY: server-move
server-move: server-build
	mkdir -p distributions && \
	rm -rf distributions/lunar-server-*.jar && \
	mv lunar-backend/lunar-server/target/lunar-server-*.jar distributions/

.PHONY: frontend
frontend: frontend-move
.PHONY: frontend-build
frontend-build:
	cd lunar-frontend/lunar-vben/ && pnpm build
.PHONY: frontend-tar
frontend-tar: frontend-build
	cd lunar-frontend/lunar-vben/dist && tar -zcvf frontend.tar.gz ./*
.PHONY: frontend-move
frontend-move: frontend-tar
	mkdir -p distributions && \
	rm -rf distributions/frontend.tar.gz && \
	mv lunar-frontend/lunar-vben/dist/frontend.tar.gz distributions/

test:
	@echo "