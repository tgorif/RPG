import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import RPG.PerkSystem.Perk;
import RPG.PerkSystem.PerkTree;
import RPG.SkillSystem.SkillData;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class init {
    static Map<String, Perk> perkMap = new HashMap<>();
    static Map<String, PerkTree> perkTreeMap = new HashMap<>();
    static void start(){
        loadPerks();
        loadPerkTree();
        loadSkillData();
    }
    private static void printPerkMap(){
        System.out.println("PerkMap: " + perkMap.size());
        for(Map.Entry<String,Perk> e : perkMap.entrySet()){
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }
    private static void printPerkTreeMap(){
        System.out.println("PerkTreeMap: " + perkTreeMap.size());
        for(Map.Entry<String,PerkTree> e : perkTreeMap.entrySet()){
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }
    private static void loadPerks(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                Perk.PerkBuilder perkBuilder;
                boolean Name = false;
                boolean type = false;
                boolean Action = false;
                boolean HP = false;
                boolean SPD = false;
                boolean movement = false;
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("perk")) {
                           perkBuilder=new Perk.PerkBuilder();
                    }
                    if (qName.equalsIgnoreCase("Name")) {
                        Name = true;
                    }
                    if (qName.equalsIgnoreCase("type")) {
                        type = true;
                    }
                    if (qName.equalsIgnoreCase("Action")) {
                        Action = true;
                    }
                    if (qName.equalsIgnoreCase("HP")) {
                        HP = true;
                    }
                    if (qName.equalsIgnoreCase("SPD")) {
                        SPD = true;
                    }
                    if (qName.equalsIgnoreCase("movement")) {
                        movement = true;
                    }
                }
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("perk")) {
                        Perk p=perkBuilder.build();
                        perkMap.put(p.name,p);
                    }
                }
                public void characters(char ch[], int start, int length) throws SAXException {
                    if (Name) {
                        perkBuilder.setName(new String(ch, start, length));
                        Name = false;
                    }
                    if (type) {
                        perkBuilder.setType(new String(ch, start, length));
                        type = false;
                    }
                    if (Action) {
                        perkBuilder.setSkill(new String(ch, start, length));
                        Action = false;
                    }
                    if (SPD) {
                        perkBuilder.setSPD(Integer.parseInt(new String(ch, start, length)));
                        SPD = false;
                    }
                    if (HP) {
                        perkBuilder.setHP(Integer.parseInt(new String(ch, start, length)));
                        HP = false;
                    }
                    if (movement) {
                        perkBuilder.setMovement(Integer.parseInt(new String(ch, start, length)));
                        movement = false;
                    }

                }

            };
            saxParser.parse("C:\\workspace\\idea\\RPG\\Resources\\Perks\\Perks.xml", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void loadPerkTree(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                String className;
                List<String> perks;
                List<String> ancestors;
                List<String> descendants;
                List<String> roots;
                boolean Name = false;
                boolean Perk = false;
                boolean ancestor = false;
                boolean descendant = false;
                boolean root=false;
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("class")) {
                        className="";
                        perks=new ArrayList<>();
                        ancestors=new ArrayList<>();
                        descendants=new ArrayList<>();
                        roots=new ArrayList<>();
                    }
                    else if (qName.equalsIgnoreCase("Name")) {
                        Name = true;
                    }
                    else if (qName.equalsIgnoreCase("Perk")) {
                        Perk = true;
                    }
                    else if (qName.equalsIgnoreCase("ancestor")) {
                        ancestor = true;
                    }
                    else if (qName.equalsIgnoreCase("descendant")) {
                        descendant = true;
                    }
                    else if (qName.equalsIgnoreCase("root")) {
                        root = true;
                    }

                }
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("class")) {
                        PerkTree p = new PerkTree(className,perks,ancestors,descendants,roots);
                        perkTreeMap.put(p.name,p);
                    }
                }
                public void characters(char ch[], int start, int length) throws SAXException {
                    if (Name) {
                        className=(new String(ch, start, length));
                        Name = false;
                    }
                    else if (Perk) {
                        perks.add(new String(ch, start, length));
                        Perk = false;
                    }
                    else if (ancestor) {
                        ancestors.add(new String(ch, start, length));
                        ancestor = false;
                    }
                    else if (descendant) {
                        descendants.add(new String(ch, start, length));
                        descendant = false;
                    }
                    else if (root) {
                        roots.add(new String(ch, start, length));
                        root = false;
                    }
                }
            };
            saxParser.parse("C:\\workspace\\idea\\RPG\\Resources\\Classes\\Class.xml", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void loadSkillData(){
        final File folder = new File(config.SKILL_FOLDER);
        for (File f :folder.listFiles()){
            loadSkillData(f.toString());
        }
    }
    private static void loadSkillData(String fileName){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                SkillData.SkillBuilder builder;
                boolean isName = false;
                boolean isTemplate=false;
                boolean isRange=false;
                boolean isCost=false;
                boolean isDamage=false;
                boolean isCoolDown=false;
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("xml")) {
                       builder=new SkillData.SkillBuilder();
                    }
                    else if (qName.equalsIgnoreCase("name")) {
                        isName=true;
                    }
                    else if (qName.equalsIgnoreCase("template")) {
                        isTemplate=true;
                    }
                    else if (qName.equalsIgnoreCase("range")) {
                        isRange=true;
                    }
                    else if (qName.equalsIgnoreCase("cost")) {
                        isCost=true;
                    }
                    else if (qName.equalsIgnoreCase("damage")) {
                        isDamage=true;
                    }
                    else if (qName.equalsIgnoreCase("CoolDown")) {
                        isCoolDown=true;
                    }


                }
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("xml")) {
                        builder.build();
                    }
                }
                public void characters(char ch[], int start, int length) throws SAXException {
                    if (isName) {
                        builder.name(new String(ch, start, length));
                        isName = false;
                    }
                    else if (isTemplate) {
                        builder.template(new String(ch, start, length));
                        isTemplate = false;
                    }
                    else if (isRange) {
                        builder.range(Integer.parseInt(new String(ch, start, length)));
                        isRange = false;
                    }
                    else if (isCost) {
                        builder.cost(Integer.parseInt(new String(ch, start, length)));
                        isCost = false;
                    }
                    else if (isDamage) {
                        builder.damage(Integer.parseInt(new String(ch, start, length)));
                        isDamage = false;
                    }
                    else if (isCoolDown) {
                        builder.coolDown(Integer.parseInt(new String(ch, start, length)));
                        isCoolDown = false;
                    }
                }
            };
            saxParser.parse(fileName, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

