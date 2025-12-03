![Logo](https://raw.githubusercontent.com/AshlynSMP/.github/refs/heads/main/assets/propaganda.png)

---
[![forthebadge](https://forthebadge.com/images/badges/contains-technical-debt.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/kinda-sfw.svg)](https://forthebadge.com)

**Propaganda** (name might need an update) is a core Minecraft plugin for the AshlynSMP server, providing essential gameplay mechanics, custom voice chat integration, and administrative tools.

## Description

Propaganda is a comprehensive Spigot/Paper plugin designed specifically for the AshlynSMP server. It manages PvP mechanics, custom lore systems, player interactions, voice chat enhancements, and server-wide broadcasts. The plugin integrates with Simple Voice Chat and ProtocolLib to deliver an enhanced multiplayer experience.

## Features

- **PvP Management**: Configurable PvP system with ceasefire (`wapenstilstand`) command
- **Custom Lore System**: Toggle-able lore functionality for immersive gameplay
- **Voice Chat Integration**: Custom voice chat features using Simple Voice Chat API (Soon)
- **Broadcast System**: Server-wide announcement system
- **End Credits**: Show the minecraft end credits
- **Chat Enhancements**: Custom chat listener with text scrambling capabilities
- **Sign Interactions**: Advanced sign change detection and handling for radio towers
- **Join/Leave Events**: Custom messages and actions on player join/leave
- **Death Events**: Enhanced player death mechanics (Soon)
- **Radio Towers**: Special towers with unique interactions and lore significance

## Commands

| Command | Aliases | Description |
|---------|---------|-------------|
| `/wapenstilstand` | - | Toggle ceasefire/PvP mode |
| `/broadcast` | `/bc` | Send server-wide announcements |
| `/lore` | - | Manage lore system |
| `/credits` | - | Display end credits |

## Configuration

The plugin includes a `config.yml` with the following options:

```yaml
pvp-disabled: false      # Enable/disable PvP globally
lore-enabled: false      # Enable/disable lore system
tower-oost: true         # Enable east tower zone
tower-west: true         # Enable west tower zone
leaders:                 # Server leaders/admins
  - 'ntnt'
  - 'TheBigCheese_420'
```

## Dependencies

**Required:**
- [Simple Voice Chat](https://modrinth.com/plugin/simple-voice-chat) (v2.6.0+)
- [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/) (v5.4.0+)


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
- [Simple Voice Chat](https://modrinth.com/plugin/simple-voice-chat)
- [ProtocolLib](https://www.spigotmc.org/resources/protocollib.1997/)

---
