#!/bin/bash

# Set the base directory and project root
PROJECT_ROOT=~/defi-tether-etherscan

# Function to install a package if not installed
install_if_not_installed() {
  local package_name=$1
  local check_command=$2
  local install_command=$3

  echo "Checking for $package_name..."
  if ! $check_command &> /dev/null; then
    echo "$package_name is not installed. Installing..."
    $install_command
    if [ $? -eq 0 ]; then
      echo "$package_name installed successfully."
    else
      echo "Failed to install $package_name. Please install it manually."
      exit 1
    fi
  else
    echo "$package_name is already installed."
  fi
}

# 1. Check for Java 11
install_if_not_installed "Java 11" "java -version | grep '11'" \
  "sudo apt update && sudo apt install -y openjdk-11-jdk"

# 2. Check for Maven
install_if_not_installed "Maven" "mvn -version" \
  "sudo apt update && sudo apt install -y maven"

# 3. Check for Docker
install_if_not_installed "Docker" "docker --version" \
  "sudo apt update && sudo apt install -y docker.io && sudo systemctl start docker && sudo systemctl enable docker"

# 4. Check for Docker Compose
install_if_not_installed "Docker Compose" "docker-compose --version" \
  "sudo curl -L \"https://github.com/docker/compose/releases/download/1.29.2/docker-compose-\$(uname -s)-\$(uname -m)\" -o /usr/local/bin/docker-compose && sudo chmod +x /usr/local/bin/docker-compose"

# 5. Check for Git
install_if_not_installed "Git" "git --version" \
  "sudo apt update && sudo apt install -y git"

# 6. Check for pnpm
install_if_not_installed "pnpm" "pnpm --version" \
  "curl -fsSL https://get.pnpm.io/install.sh | sh -"

echo "All necessary tools are installed and up-to-date."

# Instructions to source the pnpm profile in case it's a fresh install
if ! command -v pnpm &> /dev/null; then
  echo "Please run 'source ~/.bashrc' to load pnpm in your shell."
fi
