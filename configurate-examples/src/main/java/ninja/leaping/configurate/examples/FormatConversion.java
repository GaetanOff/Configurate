/*
 * Configurate
 * Copyright (C) zml and Configurate contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ninja.leaping.configurate.examples;

import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.yaml.YAMLConfigurationLoader;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * An example of how to convert a configuration between two formats.
 */
public class FormatConversion {
    public static void main(String[] args) {
        // First off: we build two loaders, one with our old format pointing to the old location
        final YAMLConfigurationLoader oldFormat = YAMLConfigurationLoader.builder()
                .setPath(Paths.get("widgets.yml"))
                .build();

        // and a second one for our target format, pointing to the new location
        final HoconConfigurationLoader newFormat = HoconConfigurationLoader.builder()
                .setPath(Paths.get("widgets.conf"))
                .build();

        // We try to load the file into a node using the source format
        final ConfigurationNode oldNode;
        try {
            oldNode = oldFormat.load();
        } catch (IOException e) {
            System.err.println("Unable to read YAML configuration: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
            System.exit(1);
            return;
        }

        // And if we're successful, we save the loaded node using the new loader
        try {
            newFormat.save(oldNode);
        } catch (IOException e) {
            System.out.println("Unable to save HOCON format configuration: " + e.getMessage());
            System.exit(2);
            return;
        }

        System.out.println("Successfully converted widgets.yml to widgets.conf!");
    }
}
