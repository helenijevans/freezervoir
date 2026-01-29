<p align="center" xmlns="http://www.w3.org/1999/html">
  <img src="./logo.png" alt="Freezervoir logo" width="300">
</p>
<p align="center"> 🚧 <strong>Status:</strong> Planning/Early Development  </p>
<p align="center"> 🏗️ <strong>Tech Stack:</strong> Java 21 • Spring Boot • Maven • Vaadin • MySQL </p>

___
## Motivation

The household freezer is often chock-a-block with leftover meals, pre-portioned ingredients, batch-cooked items, and other frozen goods. Having been brought up with annual “freezer reviews”, where everything was taken out, spread across a blanket outside, and logged, keeping track of food has been long ingrained in me. Last year, I decided there had to be a better way and designed a simple tracking system using QR-code labels (via a label maker) and a Google Sheet. This approach has been invaluable, giving clear visibility of what’s available and proving especially useful on days when I (or others) need a ready-made meal quickly.

However effective, the process is still very manual. It involves:

- Creating records 
- Printing QR stickers and placing them on freezer packaging 
- Removing QR stickers when an item is used 
- Scanning the removed stickers to retrieve their IDs 
- Updating or deleting the associated record

The final two steps are where things tend to stall, often getting postponed in favour of eating, and they leave a growing pile of stickers to deal with later.
This incentivised the need for this project, which will migrate the current process to an automated one.

The design supports future extensions, including notifications for "stagnant" items and AI-assisted meal suggestions. The continued objective is to transform the freezer from a mysterious reservoir into an active part of reducing waste, household spending, and guesswork.



## Roadmap

### MVP:
- Database-backed freezer inventory
- Web UI for item tracking
- QR scan → automatic record update

### Planned:
- Expiry and stagnation notifications
- Usage analytics
- AI-assisted meal suggestions

## Author

Helen I J  Evans  
Project: Freezervoir  
A personal project to automate and improve freezer inventory tracking.



