<img src="icon.png" align="right" width="180px"/>

# Cotton Client Commands

[![Maven metadata URL](https://img.shields.io/maven-metadata/v/http/server.bbkr.space:8081/artifactory/libs-snapshot/io/github/cottonmc/cotton-client-commands/maven-metadata.xml.svg)](http://server.bbkr.space:8081/artifactory/libs-snapshot/io/github/cottonmc/cotton-client-commands)

[>> Downloads <<](https://github.com/CottonMC/ClientCommands/releases)

A Minecraft library for 1.15 that adds support for client-side commands.

**This mod is open source and under a permissive license.** As such, it can be included in any modpack on any platform without prior permission. We appreciate hearing about people using our mods, but you do not need to ask to use them. See the [LICENSE file](LICENSE) for more details.

## Usage

Add a dependency in your `build.gradle` or `build.gradle.kts`:

```groovy
repositories {
    maven {
        name = 'CottonMC'
        url = 'http://server.bbkr.space:8081/artifactory/libs-snapshot'
    }
}

dependencies {
    modCompile "io.github.cottonmc:cotton-client-commands:<latest version>-SNAPSHOT"
}
```

<details>
    <summary>build.gradle.kts</summary><p>
    
```kotlin
repositories {
    maven(url = "http://server.bbkr.space:8081/artifactory/libs-snapshot") {
        name = "CottonMC"
    }
}

dependencies {
    modCompile("io.github.cottonmc:cotton-client-commands:<latest version>-SNAPSHOT")
}
```
</details>

Register the commands with a `ClientCommandPlugin`:

```java
import com.mojang.brigadier.CommandDispatcher;
import io.github.cottonmc.clientcommands.*;
import net.minecraft.server.command.CommandSource;
import net.minecraft.text.LiteralText;

public class MyCommands implements ClientCommandPlugin {
    @Override
    public void registerCommands(CommandDispatcher<CottonClientCommandSource> dispatcher) {
        dispatcher.register(ArgumentBuilders.literal("client-commands").executes(
            source -> {
                source.getSource().sendFeedback(new LiteralText("Hello, world!"));
                return 1;
            }
        ));
    }
}
```

And finally, add the dependency and register the plugin entrypoint in your `fabric.mod.json`:

```json
{
  "depends": {
    "cotton-client-commands": "^1.0.0"
  },
  "entrypoints": {
    "cotton-client-commands": ["path.to.MyCommands"]
  }
}
```

The classes `ArgumentBuilders` and `CottonClientCommandSource` are provided as
alternatives to `CommandManager` and `ServerCommandSource`. 
