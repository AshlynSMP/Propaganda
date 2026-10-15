![Logo](https://raw.githubusercontent.com/AshlynSMP/.github/refs/heads/main/assets/propaganda.png)

---
[![forthebadge](https://forthebadge.com/images/badges/contains-technical-debt.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/kinda-sfw.svg)](https://forthebadge.com)

**Propaganda** (name might need an update) is a core Minecraft plugin for the AshlynSMP server, providing gameplay mechanics and administrative tools that haven't been moved to their own plugin yet.

## Description

Propaganda is a comprehensive Spigot/Paper plugin designed specifically for the AshlynSMP server. It manages PvP mechanics, the lore mode toggle, join/leave/death messages and the world border wraparound.

## Features

- **PvP Management**: Configurable PvP system with ceasefire (`wapenstilstand`) command
- **Custom Lore System**: Toggle-able lore functionality for immersive gameplay
- **Join/Leave Events**: Custom messages and the op team prefix on player join/leave
- **Death Events**: Custom death message
- **World Border Wraparound**: Players near the border are teleported to the opposite side

## Commands

| Command | Aliases | Description |
|---------|---------|-------------|
| `/wapenstilstand` | - | Toggle ceasefire/PvP mode |
| `/lore` | - | Manage lore system |

## Configuration

The plugin includes a `config.yml` with the following options:

```yaml
pvp-disabled: false      # Enable/disable PvP globally
lore-enabled: false      # Enable/disable lore system
leaders:                 # Server leaders/admins
  - 'ntnt'
```

## Building from Source

### Prerequisites
- Java 16 or higher
- Maven 3.6+

### Build Steps

```bash
# Clone the repository
git clone https://github.com/AshlynSMP/Propaganda.git
cd Propaganda

# Build with Maven
mvn clean package

# The compiled jar will be in the target/ directory
```

## Requirements

- **Minecraft Version**: 1.21+
- **Server Software**: Spigot/Paper 1.21+
- **Java Version**: 16+

## License

This project is proprietary software for the AshlynSMP server.

## Bug Reports & Features

If you encounter any bugs or have feature suggestions, please open an issue on the GitHub repository.

## Links

- [Season 2 Resource Pack](https://github.com/AshlynSMP/RS-S2)
- [Radio](https://github.com/AshlynSMP/Radio)
- [Credits](https://github.com/AshlynSMP/Credits)
- [Core](https://github.com/AshlynSMP/Core)

---
