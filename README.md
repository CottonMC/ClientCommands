<img src="icon.png" align="right" width="180px"/>

# Client Commands

[>> Downloads <<](https://github.com/CottonMC/ClientCommands/releases)

A Minecraft library for 1.14 that adds support for client-side commands.

**This mod is open source and under a permissive license.** As such, it can be included in any modpack on any platform without prior permission. We appreciate hearing about people using our mods, but you do not need to ask to use them. See the [LICENSE file](LICENSE) for more details.

## Usage

Add a dependency in your `build.gradle`:

```groovy
repositories {
    maven {
        name = 'CottonMC'
        url = 'http://server.bbkr.space:8081/artifactory/libs-snapshot'
    }
}

dependencies {
    modCompile "io.github.cottonmc:client-commands:0.2.0+1.14-pre5"
}
```

Register the commands in a `ClientModInitializer`:

```java
public class ExampleMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientCommands.registerCommand(dispatcher -> {
            dispatcher.register(ArgumentBuilders.literal("client-commands").executes(
                source -> {
                    Feedback.sendFeedback(new StringTextComponent("Hello, world!"));
                    return 1;
                }
            ));
        });
    }
}
```

The classes `ArgumentBuilders` and `Feedback` are provided as
alternatives to `CommandManager` and the feedback methods in `ServerCommandSource`. 
