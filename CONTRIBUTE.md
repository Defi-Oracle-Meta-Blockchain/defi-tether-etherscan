# Contributing to DEFI TETHER ETHESCAN

Thank you for considering contributing to the DEFI TETHER ETHESCAN project! We welcome contributions from the community and are excited to work with you. This document outlines the process for contributing to the project.

## Table of Contents

1. [Code of Conduct](#code-of-conduct)
2. [How to Contribute](#how-to-contribute)
3. [Setting Up Your Development Environment](#setting-up-your-development-environment)
4. [Submitting Changes](#submitting-changes)
5. [Style Guide](#style-guide)
6. [Running Tests](#running-tests)
7. [CI/CD Pipeline](#cicd-pipeline)
8. [Documentation](#documentation)
9. [Contact](#contact)

## Code of Conduct

Please read and follow our [Code of Conduct](CODE_OF_CONDUCT.md) to ensure a welcoming and inclusive environment for everyone.

## How to Contribute

1. **Fork the repository**: Click the "Fork" button at the top right corner of the repository page.
2. **Clone your fork**: Clone your forked repository to your local machine.
   ```bash
   git clone https://github.com/your-username/defi-tether-etherscan.git
   cd defi-tether-etherscan
   ```
3. **Create a branch**: Create a new branch for your feature or bugfix.
   ```bash
   git checkout -b feature/your-feature-name
   ```
4. **Make your changes**: Implement your changes in the new branch.
5. **Commit your changes**: Commit your changes with a descriptive commit message.
   ```bash
   git add .
   git commit -m "feat: Add new feature"
   ```
6. **Push your changes**: Push your changes to your forked repository.
   ```bash
   git push origin feature/your-feature-name
   ```
7. **Create a pull request**: Open a pull request from your forked repository to the main repository.

## Setting Up Your Development Environment

### Prerequisites

Ensure you have the following installed on your system:

- Java 11
- Maven
- Docker
- Docker Compose
- Git

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/defi-tether-etherscan/defi-tether-etherscan.git
   cd defi-tether-etherscan
   ```
2. **Set up environment variables**: Create a `.env` file in the root directory and add the necessary environment variables.
   ```bash
   cp .env.example .env
   ```
3. **Build the project**: Use Maven to build the project.
   ```bash
   mvn clean install
   ```
4. **Run Docker containers**: Use Docker Compose to start the necessary services.
   ```bash
   docker-compose up -d
   ```

## Submitting Changes

1. **Ensure your changes pass all tests**: Run the tests locally before submitting your pull request.
2. **Update documentation**: If your changes affect the documentation, update the relevant sections in the `docs/` directory.
3. **Create a pull request**: Open a pull request with a clear description of your changes and any relevant issue numbers.

## Style Guide

Please follow the coding standards and style guide used in the project:

- **Java**: Follow the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).
- **Solidity**: Follow the [Solidity Style Guide](https://docs.soliditylang.org/en/v0.8.6/style-guide.html).
- **JavaScript**: Follow the [Airbnb JavaScript Style Guide](https://github.com/airbnb/javascript).

## Running Tests

1. **Unit tests**: Run unit tests using Maven.
   ```bash
   mvn test
   ```
2. **Integration tests**: Run integration tests using Maven.
   ```bash
   mvn verify
   ```

## CI/CD Pipeline

The project uses GitHub Actions for continuous integration and deployment. The CI pipeline is defined in the `.github/workflows/ci.yml` file. Ensure your changes pass all checks in the CI pipeline before submitting a pull request.

## Documentation

Please ensure that any new features or changes are well-documented. Update the relevant sections in the `docs/` directory and the `README.md` file as needed.

## Contact

If you have any questions or need further assistance, feel free to reach out to the project maintainers or open an issue on GitHub.

Thank you for contributing to DEFI TETHER ETHESCAN!
