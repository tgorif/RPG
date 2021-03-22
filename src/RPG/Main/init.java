package RPG.Main;

import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import RPG.Character.CharacterPool;
import RPG.PerkSystem.Perk;
import RPG.PerkSystem.PerkTree;
import RPG.Projectiles.ProjectileData;
import RPG.SkillSystem.SkillData;
import RPG.StatusEffects.StatusData;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class init {
    static Map<String, Perk> perkMap = new HashMap<>();
    static Map<String, PerkTree> perkTreeMap = new HashMap<>();
    final static Logger LOGGER = Logger.getLogger(init.class.getName());
    public static void start(){
        loadPerks();
        loadPerkTrees();
        loadSkillData();
        loadProjectiles();
        loadStatusEffects();
        loadCharacterPool();
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
        final File folder = new File(config.PERK_FOLDER);
        if(!folder.exists()) LOGGER.log(Level.SEVERE,"PerkFolder does not exist");
        else {
            for (File f : Objects.requireNonNull(folder.listFiles())) {
                loadPerk(f.toString());
            }
        }
    }
    private static void loadPerk(String fileName){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                Perk.PerkBuilder perkBuilder;
                boolean Name = false;
                boolean type = false;
                boolean SkillName = false;
                boolean HP = false;
                boolean SPD = false;
                boolean movement = false;
                boolean armor= false;
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
                    if (qName.equalsIgnoreCase("SkillName")) {
                        SkillName = true;
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
                    if (qName.equalsIgnoreCase("armor")) {
                        armor = true;
                    }
                }
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("perk")) {
                        Perk p=perkBuilder.build();
                        perkMap.put(p.name,p);
                    }
                }
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (Name) {
                        perkBuilder.setName(new String(ch, start, length));
                        Name = false;
                    }
                    if (type) {
                        perkBuilder.setType(new String(ch, start, length));
                        type = false;
                    }
                    if (SkillName) {
                        perkBuilder.setSkill(new String(ch, start, length));
                        SkillName = false;
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
                    if (armor) {
                        perkBuilder.setArmor(Integer.parseInt(new String(ch, start, length)));
                        armor = false;
                    }
                }
            };
            saxParser.parse(fileName, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void loadPerkTrees(){
        final File folder = new File(config.CLASS_FOLDER);
        if(!folder.exists()) LOGGER.log(Level.SEVERE,"ClassFolder does not exist");
        else{
            for (File f : Objects.requireNonNull(folder.listFiles())) {
                loadPerkTree(f.toString());
            }
        }
    }
    private static void loadPerkTree(String fileName){
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
                public void characters(char[] ch, int start, int length) throws SAXException {
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
            saxParser.parse(fileName, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void loadSkillData(){
        final File folder = new File(config.SKILL_FOLDER);
        if(!folder.exists()) LOGGER.log(Level.SEVERE,"SkillFolder does not exist");
        else{
            for (File f : Objects.requireNonNull(folder.listFiles())) {
                loadSkillData(f.toString());
            }
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
                boolean isProjectile=false;
                boolean isReviveHP=false;
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
                    else if(qName.equalsIgnoreCase("projectile")){
                        isProjectile=true;
                    }
                    else if(qName.equalsIgnoreCase("reviveHP")){
                        isReviveHP=true;
                    }
                }
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("xml")) {
                        builder.build();
                    }
                }
                public void characters(char[] ch, int start, int length) throws SAXException {
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
                    else if(isProjectile){
                        builder.projectile(new String(ch, start, length));
                        isProjectile=false;
                    }
                    else if(isReviveHP){
                        builder.reviveHP(Integer.parseInt(new String(ch, start, length)));
                        isReviveHP=false;
                    }
                }
            };
            saxParser.parse(fileName, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void loadProjectiles(){
        final File folder = new File(config.PROJECTILE_FOLDER);
        if(!folder.exists()) LOGGER.log(Level.SEVERE,"Projectile Folder does not exist");
        else{
            for (File f : Objects.requireNonNull(folder.listFiles())) {
                loadProjectile(f.toString());
            }
        }
    }
    private static void loadProjectile(String fileName){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                ProjectileData.ProjectileBuilder builder;
                boolean isName = false;
                boolean isTemplate=false;
                final boolean isRange=false;
                final boolean isCost=false;
                final boolean isDamage=false;
                final boolean isCoolDown=false;
                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("xml")) {
                        builder=new ProjectileData.ProjectileBuilder();
                    }
                    else if (qName.equalsIgnoreCase("name")) {
                        isName=true;
                    }
                    else if (qName.equalsIgnoreCase("template")) {
                        isTemplate=true;
                    }
                }
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("xml")) {
                        builder.build();
                    }
                }
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (isName) {
                        builder.name(new String(ch, start, length));
                        isName = false;
                    }
                    else if (isTemplate) {
                        builder.template(new String(ch, start, length));
                        isTemplate = false;
                    }
                }
            };
            saxParser.parse(fileName, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void loadStatusEffects(){
        final File folder = new File(config.STATUS_FOLDER);
        if(!folder.exists()) LOGGER.log(Level.SEVERE,"StatusEffect Folder does not exist");
        else{
            for (File f : Objects.requireNonNull(folder.listFiles())) {
                loadStatusEffect(f.toString());
            }
        }
    }
    private static void loadStatusEffect(String fileName){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                StatusData.StatusBuilder builder;
                boolean isName = false;
                boolean isTemplate=false;
                boolean isChange=false;
                boolean isDuration=false;

                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("xml")) {
                        builder=new StatusData.StatusBuilder();
                    }
                    else if (qName.equalsIgnoreCase("name")) {
                        isName=true;
                    }
                    else if (qName.equalsIgnoreCase("template")) {
                        isTemplate=true;
                    }
                    else if(qName.equalsIgnoreCase("change")){
                        isChange=true;
                    }
                    else if(qName.equalsIgnoreCase("duration")){
                        isDuration=true;
                    }
                }
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("xml")) {
                        builder.build();
                    }
                }
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (isName) {
                        builder.name(new String(ch, start, length));
                        isName = false;
                    }
                    else if (isTemplate) {
                        builder.template(new String(ch, start, length));
                        isTemplate = false;
                    }
                    else if (isChange) {
                        builder.HPChange(Integer.parseInt(new String(ch, start, length)));
                        isChange = false;
                    }
                    else if (isDuration) {
                        builder.duration(Integer.parseInt(new String(ch, start, length)));
                        isDuration = false;
                    }
                }
            };
            saxParser.parse(fileName, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void loadCharacterPool(){
        final File folder = new File(config.CHARACTER_POOL_FOLDER);
        if(!folder.exists()) LOGGER.log(Level.SEVERE,"CharacterPool Folder does not exist");
        else{
            for (File f : Objects.requireNonNull(folder.listFiles())) {
                loadCharacter(f.toString());
            }
        }
    }
    private static void loadCharacter(String fileName){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                CharacterPool.CharacterData characterData=null;
                boolean isName = false;
                boolean isPerkTree=false;
                boolean isPerk=false;

                public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("name")) {
                        isName=true;
                    }
                    else if (qName.equalsIgnoreCase("Class")) {
                        isPerkTree=true;
                    }
                    else if(qName.equalsIgnoreCase("Perk")){
                        isPerk=true;
                    }
                }
                public void endElement(String uri, String localName, String qName) throws SAXException {

                }
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (isName) {
                        characterData =new CharacterPool.CharacterData(new String(ch, start, length));
                        isName = false;
                    }
                    else if (isPerkTree) {
                        characterData.addPerkTree(new String(ch, start, length));
                        isPerkTree = false;
                    }
                    else if (isPerk) {
                        characterData.addPerk(new String(ch, start, length));
                        isPerk = false;
                    }
                }
            };
            saxParser.parse(fileName, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

