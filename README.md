# FabriComm Site

Source files used to generate [The Fabric Community's site](https://fabricmc.community).

The site is generated using [a set of templates](/templates), which are filled with content rendered from
[a set of Markdown and HTML files](/site). This process is handled by
[a custom static site generator](https://github.com/FabriCommunity/SSG).

# Working with the site

In order to make working with the site easier, a number of scripts have been provided. They come in two formats -
batch (`.bat`) scripts for Windows users and bash (`.sh`) scripts for everyone else - and you can run them in your
terminal of choice.

The static site generator requires that you have Java 8 or later installed.

* `build` - Build the site, generating a copy in the `build` folder.
* `download` - Download (or update) the static site generator. This is run automatically by the `build` script if
  `SSG.jar` is not present. If the scripts aren't working, you'll probably need to delete `SSG.jar` or just run this
  script yourself.
* `serve` - Spin up a simple webserver on localhost to serve the site after you've built it. This will not build it
  automatically - run the `build` script whenever you need to do that. Press `CTRL + C` at any point to stop the server.

## As a writer

If you'd simply like to contribute a document or tutorial (or edit one that exists), you can create Markdown files
within [the `site` folder](/site). All Markdown files should have the `.md` or `.md.peb` file extension (the latter
should be used for Markdown files that contain Pebble templates, but this is optional).

The site is divided into a number of sections, each with its own navigation. Sections are represented by sub-folders
in [the `site` folder](/site), aside from the `static` folder (which contains static assets). This helps to keep things
organised, with different sets of documents kept separately from each other.

The following [Markdown extensions](https://github.com/vsch/flexmark-java/wiki/Extensions) are enabled:

* [Abbreviations](https://github.com/vsch/flexmark-java/wiki/Extensions#abbreviation)
* [Anchor Links](https://github.com/vsch/flexmark-java/wiki/Extensions#anchorlink)
* [Attributes](https://github.com/vsch/flexmark-java/wiki/Extensions#attributes)
* [Autolink](https://github.com/vsch/flexmark-java/wiki/Extensions#autolink)
* [Emoji](https://github.com/vsch/flexmark-java/wiki/Extensions#emoji) (`:emoji_name:`)
* [Media Tags](https://github.com/vsch/flexmark-java/wiki/Extensions#media-tags)
* [Strikethrough](https://github.com/vsch/flexmark-java/wiki/Extensions#gfm-strikethroughsubscript)
* [Table of Contents](https://github.com/vsch/flexmark-java/wiki/Extensions#table-of-contents)
* [Tables](https://github.com/vsch/flexmark-java/wiki/Extensions#tables)
* [WikiLinks](https://github.com/vsch/flexmark-java/wiki/Extensions#wikilinks)
* [YouTube Embedded Link Transformer](https://github.com/vsch/flexmark-java/wiki/Extensions#youtube-embedded-link-transformer)

### Front matter

All Markdown files must start with a front matter block. Front matter blocks contain metadata about the current document
in YAML format, placed within two sets of triple-dashes (`---`). For example:

```markdown
---
title: My Document Title template: fabric/tutorial

authors:

- My Name

---

Document content here
```

Front matter may contain the following:

* **Required**: `title` - The document's title, displayed on the site.
* Optional: `template` - If your document uses a template other than the default then you can specify it here, without
  the `.html.peb` extension. Template names are relative to the `templates` folder - for example,
  `templates/fabric/tutorial.html.peb` becomes `fabric/tutorial`.
* Optional: `authors` - For tutorials and other contributed documents, a list of authors may be provided. This should be
  a simple list of names, and should not contain any HTML.

### Navigation

Each section, as well as the site root, may contain a `navigation.yml` file. This file defines how the section's
navigation should be structured. It consists of a set of navigation nodes, with their own properties. For example:

```yaml
nodes:
  - title: "One"
    path: "/one"
    icon: "globe"

  - title: "Two"
    path: "/two"
    icon: "arrow-right"

    children:
      - title: "Three"
        path: "/two/three"
        icon: "car"
```

Each node has the following properties:

* **Required:** `icon` - The name of a font-awesome icon (including pro icons) to use for this navigation node.
* **Required:** `path` - The absolute path of the document this node should take you to when clicked. This may be the
  path to a document, a static file (in `/static`), or a full URL (eg `https://google.com`). If you're referring to a
  document in the current section, **you should always use an absolute path that matches the url**.
* **Required:** `title` - The name of the navigation node that's shown on the site.
* Optional: `children` - Other nodes that should be placed within this node.
* Optional: `description` - A short description describing where this node points at.

### Conventions and tips

When writing for the site, please take note of the following:

* All filenames should use `lowered-kebab-case`. For example, instead of `persons_file.md` or `Persons-File.md`, use
  `persons-file.md`. We also recommend avoiding the use of non-alphanumeric characters so that filenames will match
  their URLs - so that's letters, numbers, and the dash (`-`) symbol.
* **You must have the rights to all code examples you use in your contributions.** This is because all code contributed
  to our documentation is licensed under the [Creative Commons Zero License](/LICENSE-CC0.md), which effectively makes
  it public domain. If you have concerns about this policy, please contact one of the admins on Discord or raise an
  issue.
* If you need to add images for your documents, they should similarly be placed within the `static/images` folder, and
  referred to using an absolute path - for example, `![alt text](/static/images/path-to-file.png)`. In order to keep
  things organised, images should be placed within folders that match the path of the document they're used in, where
  possible - for example, if your document is in `site/fabric/tutorials/my-tutorial.md` then your images should be in
  `static/images/fabric/tutorials/my-tutorial/`.
* We recommend that everyone writing documents and articles for the site makes use of
  [LanguageTool](https://languagetool.org/). This will help to ensure a uniform (and correct) grammatical style
  throughout the site. While we don't plan on enforcing usage of this tool (and have no way to do so), we may run
  contributions through it to check for grammatical and spelling errors.

### Templating

All Markdown files are also [Pebble templates](https://pebbletemplates.io/). For the most part, this is unlikely to be
useful to most writers - but the capability is there if it's needed.

Working with templates is described in the **As a designer** section below. However, Markdown files have access to an
additional variable, `meta`, which contains the metadata that was defined within the front matter at the top of the
file. However, note that Markdown files with unsupported metadata in the front matter will fail to parse, so you can't
add any _extra_ data to the front matter.

## As a designer

As well as the documents and articles that make up the majority of the site's contents, this repository contains the
plain HTML and assets that are used to structure and style the site. Thus, as a designer, you are able to work in the
following ways:

* Using plain HTML (`.html`) or Pebble-templated HTML (`.html.peb`) files in the `site` folder, which will be rendered
  as part of the site's contents similarly to the Markdown-format files.
* By writing Pebble templates to be used by other documents and files in the `site` folder, which you can place in
  `.html.peb` files in the `templates` folder. Templates should follow the same `lower-kebab-case` naming convention as
  the documents, as described above.
* By working with static assets, which are stored in the `static` folder. By convention:

    * All CSS files should be placed in `static/css`.
    * All images should be placed in `static/images`.
    * All JavaScript scripts should be placed in `static/js`.

**Note:** Templates in the `site/` folder are temporarily written to `templates/TEMP.html.peb` during rendering. This
allows them to reference other templates, as if they themselves were placed in the `templates/` folder.

### Templating

As mentioned before, the static site generator makes use of [Pebble templates](https://pebbletemplates.io/). These allow
for re-use of HTML, which helps to keep the repository clean and easy to understand.

The following variables are available to all Pebble templates, including in Markdown files:

* `navigation` - a Navigation `Root` object, containing the following properties:
    * `currentPath` - A string representing the path to the current file.
    * `nodes` - A list of navigation `Node` objects, with the following properties:
        * `icon` - The name of the icon to use for this navigation node.
        * `path` - A string representing the path to navigate to, which may be a URL.
        * `title` - The name to display on the page.
        * `children` - A set of `Node` objects which should be placed under this one.

If a section (or the site root) doesn't have a `navigation.yml`, then `nodes` will be an empty list.

The following variables are available to Pebble templates within the `templates` folder only:

* `body` - The HTML rendered from a Markdown document. If the current document was not a Markdown document, this will
  explicitly be `null`. As a result, we recommend wrapping usages in a block named `content`, which may be replaced by
  other Pebble templates.

The following variables are available to Markdown document templates within the `site` folder only:

* `meta` - A `FrontMatter` object representing this document's front matter, containing the `title`, nullable
  `template` and `authors` properties.

# Licensing

This repository (and the site itself) is dual-licensed. For more information, please read
[the LICENSE.md file](/LICENSE.md).
