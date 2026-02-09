<p align="center" xmlns="http://www.w3.org/1999/html">
  <img src="media/logo_cropped.png" alt="Freezervoir logo" width="300">
</p>
<p align="center"> 🚧 <strong>Status:</strong> Early Development, Stage 1 (Complete)  </p>
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


___
## Roadmap
The below table shows the initial planned stages of the project. These are subject to change as the project evolves.

| Stage | Title | Description | Status |
|------:|-------|-------------|--------|
| 1 | View Current Freezer Items | Created a MySQL representation of the current Google Sheet data, added JPA architecture to read from the database, and added frontend logic to display items in a table. | ✅  |
| 2 | CRUD Basics | Add frontend functionality to add and delete items, validate these actions, and communicate correctly with the backend. After this stage, the original system will be fully replicated. |  |
| 3 | QR Integration (Core Feature) | Update QR codes to point to delete endpoints. The reason the project was started. | |
| 4 | UI Improvements | Show the number of current items and improve the visual layout and clarity of the UI. | |
| 5 | Deployment | Deploy the application so the server runs continuously. | |
| 6 | Data Model Improvements | Change IDs to be auto-generated and add a description field. |  |
| 7 | App-Style Frontend | Improve the overall UI/UX and support viewing all items in a cleaner, more app-like interface. | |
| 8 | QR Code Generation & Scanning | Generate QR codes for new entries, integrate with the label maker workflow, and support scanning QR codes in-app to add and delete items from the database. |  |
| 9 | Expiry Tracking | Notify when an item is approaching expiry (e.g. 1 year from date added). |  |
| 10 | UX & Workflow Cleanup | Improve branding and overall UX, and refine the app → label workflow. | |
| 11 | AI Enhancements | Suggest meals based on freezer contents. Replace manual data entry with camera-based capture. Use AI to suggest item descriptions and estimate realistic expiry dates. | |

___
## ▶ Run Locally
To use this project, you will need to do the following:
### Prerequisites

- Java 21 installed and set as your active JDK 
- Maven (or use the Maven Wrapper included in the project)
- MySQL running locally

### Steps

1. Clone the repository

```bash
git clone git@github.com:helenijevans/freezervoir.git
cd freezervoir
```


2. Set up the database 
   - Start your MySQL server 
   - Run the [provided SQL setup script](src/main/resources/init_db.sql) to create the freezer_items table 
   - Use SQL commands to insert your own data into freezer_items 

3. Configure database credentials 
   - Either:
     - Set environment variables for MYSQL_USERNAME and MYSQL_PASSWORD 
     - Edit `src/main/resources/application.yaml` and set your username/password directly. 
4. Run the application 
   - From IDE: Open the project → Run the Application class 
   - From the terminal: ```mvn spring-boot:run```
5. Open in browser at http://localhost:8080

### Result
You should see:
- The Freezervoir header
- A grid listing your current freezer items from the database

![current_state_ui.png](media/ui_v1.1.png)
