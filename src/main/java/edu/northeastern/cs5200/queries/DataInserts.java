package edu.northeastern.cs5200.queries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import edu.northeastern.cs5200.Dao.DeveloperDao;
import edu.northeastern.cs5200.Dao.PageDao;
import edu.northeastern.cs5200.Dao.RoleDao;
import edu.northeastern.cs5200.Dao.WebsiteDao;
import edu.northeastern.cs5200.Dao.WidgetDao;
import edu.northeastern.cs5200.model.Developer;
import edu.northeastern.cs5200.model.HeadingWidget;
import edu.northeastern.cs5200.model.HtmlWidget;
import edu.northeastern.cs5200.model.ImageWidget;
import edu.northeastern.cs5200.model.Page;
import edu.northeastern.cs5200.model.Person;
import edu.northeastern.cs5200.model.Role;
import edu.northeastern.cs5200.model.Website;
import edu.northeastern.cs5200.model.Widget;
import edu.northeastern.cs5200.model.YoutubeWidget;

public class DataInserts {

	public static void main(String[] args) {

		/**
		 * 1. Create the following developers and users. Insert into the correct tables depending on the type
		 */
		DeveloperDao ddao = new DeveloperDao();
		Person p1 = new Person("Alice", "Wonder", "alice", "alice", "alice@wonder.com", new Date());
		Developer d1 = new Developer(p1, "4321rewq");
		int d1id = ddao.createDeveloper(d1);
		Person p2 = new Person("Bob", "Marley", "bob", "bob", "bob@marley.com", new Date());
		Developer d2 = new Developer(p2, "5432trew");
		int d2id = ddao.createDeveloper(d2);
		Person p3 = new Person("Charles", "Garcia", "charlie", "charlie", "charles@garcia.com", new Date());
		Developer d3 = new Developer(p3, "6543ytre");
		int d3id = ddao.createDeveloper(d3);
		Person p4 = new Person("Dan", "Martin", "dan", "dan", "dan@martin.com", new Date());
		Developer d4 = new Developer(p4, "7654fda");
		Person p5 = new Person("Ed", "Karaz", "ed", "ed", "ed@karaz.com", new Date());
		Developer d5 = new Developer(p5, "5678dfgh");


		/**
		 * 2. Create the following web sites for the developers above. For both the created field and updated field, use the date your assignment will be graded, e.g., do not hardcode it
		 */
		WebsiteDao wdao = new WebsiteDao();
		Website w1 = new Website("Facebook", "an online social media and social networking seidice", new Date(),
				new Date(), 1234234);
		int w1id = wdao.createWebsiteForDeveloper(d1id, w1);
		Website w2 = new Website("Twitter", "an online news and social networking seidice", new Date(), new Date(),
				4321543);
		int w2id = wdao.createWebsiteForDeveloper(d2id, w2);
		Website w3 = new Website("Wikipedia", "a free online encyclopedia", new Date(), new Date(), 3456654);
		int w3id = wdao.createWebsiteForDeveloper(d3id, w3);
		Website w4 = new Website("CNN", "an American basic cable and satellite television news channel", new Date(),
				new Date(), 6543345);
		int w4id = wdao.createWebsiteForDeveloper(d1id, w4);
		Website w5 = new Website("CNET",
				"an American media website that publishes reviews, news, articles, blogs, podcasts and videos on technology and consumer electronics",
				new Date(), new Date(), 5433455);
		int w5id = wdao.createWebsiteForDeveloper(d2id, w5);
		Website w6 = new Website("Gizmodo",
				"a design, technology, science and science fiction website that also writes articles on politics",
				new Date(), new Date(), 4322345);
		int w6id = wdao.createWebsiteForDeveloper(d3id, w6);

		RoleDao rdao = new RoleDao();
		rdao.assignWebsiteRole(d1id, w1id, Role.OWNER.ordinal());
		rdao.assignWebsiteRole(d2id, w1id, Role.EDITOR.ordinal());
		rdao.assignWebsiteRole(d3id, w1id, Role.ADMIN.ordinal());

		rdao.assignWebsiteRole(d2id, w2id, Role.OWNER.ordinal());
		rdao.assignWebsiteRole(d3id, w2id, Role.EDITOR.ordinal());
		rdao.assignWebsiteRole(d1id, w2id, Role.ADMIN.ordinal());

		rdao.assignWebsiteRole(d3id, w3id, Role.OWNER.ordinal());
		rdao.assignWebsiteRole(d1id, w3id, Role.EDITOR.ordinal());
		rdao.assignWebsiteRole(d2id, w3id, Role.ADMIN.ordinal());

		rdao.assignWebsiteRole(d1id, w4id, Role.OWNER.ordinal());
		rdao.assignWebsiteRole(d2id, w4id, Role.EDITOR.ordinal());
		rdao.assignWebsiteRole(d3id, w4id, Role.ADMIN.ordinal());

		rdao.assignWebsiteRole(d2id, w5id, Role.OWNER.ordinal());
		rdao.assignWebsiteRole(d3id, w5id, Role.EDITOR.ordinal());
		rdao.assignWebsiteRole(d1id, w5id, Role.ADMIN.ordinal());

		rdao.assignWebsiteRole(d3id, w6id, Role.OWNER.ordinal());
		rdao.assignWebsiteRole(d1id, w6id, Role.EDITOR.ordinal());
		rdao.assignWebsiteRole(d2id, w6id, Role.ADMIN.ordinal());

		/**
		 * 3. Create the following pages for the web sites above. Use the semester's start date for the created field. Use the assignment's due date for the updated field
		 */
		PageDao pdao = new PageDao();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startdate = null;
		Date updateDate = null;
		try {
			startdate = formatter.parse("2018-05-06 12:00:00"); 
			updateDate = formatter.parse("2018-06-24 11:59:00"); 
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Page page1 = new Page(w5, "Home", "Landing page", startdate, updateDate, 123434);
		int p1id = pdao.createPageForWebsite(w5id, page1);
		Page page2 = new Page(w6, "About", "Website description", startdate, updateDate, 234545);
		int p2id = pdao.createPageForWebsite(w6id, page2);
		Page page3 = new Page(w3, "Contact", "Addresses, phones, and contact info", startdate, updateDate, 345656);
		int p3id = pdao.createPageForWebsite(w3id, page3);
		Page page4 = new Page(w4, "Preferences", "Where users can configure their preferences", startdate, updateDate, 456776);
		int p4id = pdao.createPageForWebsite(w4id, page4);
		Page page5 = new Page(w5, "Profile", "Users can configure their personal information", startdate, updateDate, 567878);
		int p5id = pdao.createPageForWebsite(w5id, page5);

		rdao.assignPageRole(d1id, p1id, Role.OWNER.ordinal());
		rdao.assignPageRole(d2id, p1id, Role.EDITOR.ordinal());
		rdao.assignPageRole(d3id, p1id, Role.ADMIN.ordinal());

		rdao.assignPageRole(d2id, p2id, Role.OWNER.ordinal());
		rdao.assignPageRole(d3id, p2id, Role.EDITOR.ordinal());
		rdao.assignPageRole(d1id, p2id, Role.ADMIN.ordinal());

		rdao.assignPageRole(d3id, p3id, Role.OWNER.ordinal());
		rdao.assignPageRole(d1id, p3id, Role.EDITOR.ordinal());
		rdao.assignPageRole(d2id, p3id, Role.ADMIN.ordinal());

		rdao.assignPageRole(d1id, p4id, Role.OWNER.ordinal());
		rdao.assignPageRole(d2id, p4id, Role.EDITOR.ordinal());
		rdao.assignPageRole(d3id, p4id, Role.ADMIN.ordinal());

		rdao.assignPageRole(d2id, p5id, Role.OWNER.ordinal());
		rdao.assignPageRole(d3id, p5id, Role.EDITOR.ordinal());
		rdao.assignPageRole(d1id, p5id, Role.ADMIN.ordinal());

		/**
		 * 4. Create the following widgets for the pages shown.
		 */
		WidgetDao widao = new WidgetDao();
		Widget wid1 = new Widget(page1, "head123", 0, 0, null, null, "Welcome", 0);
		HeadingWidget hw1 = new HeadingWidget(wid1, 10);
		widao.createWidgetForPage(p1id, hw1);
		Widget wid2 = new Widget(page2, "post234", 0, 0, null, null, "<p>Lorem</p>", 0);
		HtmlWidget hw2 = new HtmlWidget(wid2, "<p>Lorem</p>");
		widao.createWidgetForPage(p2id, hw2);
		Widget wid3 = new Widget(page3, "head345", 0, 0, null, null, "Hi", 1);
		HeadingWidget hw3 = new HeadingWidget(wid3, 10);
		widao.createWidgetForPage(p3id, hw3);
		Widget wid4 = new Widget(page3, "intro456", 0, 0, null, null, "<h1>Hi</h1>", 2);
		HtmlWidget hw4 = new HtmlWidget(wid4, "<h1>Hi</hi>");
		widao.createWidgetForPage(p3id, hw4);
		Widget wid5 = new Widget(page3, "image345", 50, 100, null, null, null, 3);
		ImageWidget hw5 = new ImageWidget(wid5, "/img/567.png");
		widao.createWidgetForPage(p3id, hw5);
		Widget wid6 = new Widget(page4, "video456", 400, 300, null, null, null, 0);
		YoutubeWidget hw6 = new YoutubeWidget(wid6, "https://youtu.be/h67VX51QXiQ", false, false);
		widao.createWidgetForPage(p4id, hw6);


		/**
		 * Update queries
		 */


		/**
		 * 1. Update widget - Update the relative order of widget head345 on the page so that it's new order is 3. Note that the other widget's order needs to update as well
		 */
		Collection<Widget> wids = widao.findWidgetsForPage(p3id);
		for (Widget w : wids) {
			w.setOrder(w.getOrder() + 2);
			widao.updateWidget(w.getId(), w);
		}

		/**
		 * 2. Update page - Append 'CNET - ' to the beginning of all CNET's page titles
		 */
		Collection<Page> pages = pdao.findAllPages();
		for (Page p: pages) {
			if (p.getWebsite().getName().contains("CNET")) {
				String newTitle = p.getTitle();
				p.setTitle("CNET -" + newTitle);
				pdao.updatePage(p.getId(), p);
			}
		}

		/**
		 * 3. Update roles - Swap Charlie's and Bob's role in CNET's Home page
		 */
		int bobid = 0;
		int charlieid = 0;
		int cnetid = 0;
		Collection<Page> pages1 = pdao.findAllPages();
		for (Page p: pages1) {
			if (p.getWebsite().getName().contains("CNET")) {
				cnetid = p.getId();
			}
		}

		Collection<Developer> developers = ddao.findAllDevelopers();
		for (Developer d: developers) {
			if (d.getFirstName().contains("Charles")) {
				charlieid = d.getId();
			}

			if (d.getFirstName().contains("Bob")) {
				bobid = d.getId();
			}
		}

		Role charlierole = rdao.getPageRole(charlieid, cnetid);
		Role bobrole = rdao.getPageRole(bobid, cnetid);
		rdao.deletePageRole(charlieid, cnetid, charlierole.ordinal());
		rdao.assignPageRole(charlieid, cnetid, bobrole.ordinal());
		rdao.deletePageRole(bobid, cnetid, bobrole.ordinal());
		rdao.assignPageRole(bobid, cnetid, charlierole.ordinal());


		/**
		 * Delete queries using Dao and models
		 */

		/**
		 * 1. Delete widget - Remove the last widget in the Contact page. The last widget is the one with the highest value in the order field
		 */
		Collection<Widget> widgets = widao.findAllWidgets();
		int maxOrder = 0;
		Widget maxOrderWidget = null;
		for (Widget w : widgets) {
			if (w.getOrder() > maxOrder) {
				maxOrder = w.getOrder();
				maxOrderWidget = w;
			}
		}
		widao.deleteWidget(maxOrderWidget.getId());

		/**
		 * Delete page - Remove the last updated page in Wikipedia
		 */
		Date latestUpdated = new Date(0);
		Page latestUpdatedPage = null;
		Collection<Page> pages11 = pdao.findAllPages();
		for (Page p: pages11) {
			if (latestUpdated.before(p.getUpdated())) {
				latestUpdated = p.getUpdated();
				latestUpdatedPage = p;
			}
		}
		pdao.deletePage(latestUpdatedPage.getId());

		/**
		 * 3. Delete website - Remove the CNET web site, as well as all related roles and privileges relating developers to the Website and Pages
		 */
		Collection<Website> websites = wdao.findAllWebsites();
		for (Website w: websites) {
			if (w.getName().contains("CNET")) {
				wdao.deleteWebsite(w.getId());
				break;
			}
		}
	}



}
