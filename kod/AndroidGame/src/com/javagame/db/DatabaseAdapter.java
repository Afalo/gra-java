package com.javagame.db;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.javagame.R;
import com.javagame.db.model.AnswerModel;
import com.javagame.db.model.PictureModel;
import com.javagame.exception.NoPictureNextException;
import com.javagame.exception.ObjectNotFoundException;
import com.javagame.utils.StaticHelper;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;

public class DatabaseAdapter {

	private static final String DEBUG_TAG = "DatabaseAdapter";

	private static final int DB_VERSION = 21;
    private static final String DB_NAME = "database.db";
    private static final String DB_ANSWERS_TABLE = "answers";

    public static final String ID_KEY = "id";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int ID_COLUMN = 0;

    public static final String DESCRIPTION_KEY = "description";
    public static final String DESCRIPTION_OPTIONS = "TEXT NOT NULL";
    public static final int DESCRIPTION_COLUMN = 1;

    public static final String PICTURE_ID_KEY = "picture_id";
    public static final String PICTURE_ID_OPTIONS = "INTEGER";
    public static final int PICTURE_ID_COLUMN = 2;

    public static final String LANG_VERSION_KEY = "lang_version";
    public static final String LANG_VERSION_OPTIONS = "TEXT NOT NULL";
    public static final int LANG_VERSION_COLUMN = 3;

	private static final String DB_CREATE_ANSWERS_TABLE = "CREATE TABLE "
			+ DB_ANSWERS_TABLE + "( " + ID_KEY + " " + ID_OPTIONS + ", "
			+ DESCRIPTION_KEY + " " + DESCRIPTION_OPTIONS + ", "
			+ PICTURE_ID_KEY + " " + PICTURE_ID_OPTIONS + ", "
			+ LANG_VERSION_KEY + " " + LANG_VERSION_OPTIONS + ");";

	private static final String DROP_ANSWERS_TABLE = "DROP TABLE IF EXISTS "
			+ DB_ANSWERS_TABLE;

	private static final String DB_IMAGE_TABLE = "images";

	public static final String IMAGE_ID_KEY = "id";
    public static final String IMAGE_ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int IMAGE_ID_COLUMN = 0;

    public static final String IMAGE_PICTURE_KEY = "picture";
    public static final String IMAGE_PICTURE_OPTIONS = "BLOB NOT NULL";
    public static final int IMAGE_PICTURE_COLUMN = 1;

    private static final String DB_CREATE_IMAGE_TABLE = "CREATE TABLE "
			+ DB_IMAGE_TABLE + "( " + IMAGE_ID_KEY + " " + IMAGE_ID_OPTIONS + ", "
			+ IMAGE_PICTURE_KEY + " " + IMAGE_PICTURE_OPTIONS + ");";

    private static final String DROP_IMAGE_TABLE = "DROP TABLE IF EXISTS "
			+ DB_IMAGE_TABLE;

	private SQLiteDatabase db;
	private final Context context;
	private DatabaseHelper dbHelper;

	public DatabaseAdapter(Context context) {
		this.context = context;
	}

	public DatabaseAdapter open(Resources applicationResources){
	    dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION, applicationResources);
	    try {
	        db = dbHelper.getWritableDatabase();
	    } catch (SQLException e) {
	        db = dbHelper.getReadableDatabase();
	    }
	    return this;
	}

	public void close() {
	    dbHelper.close();
	}

	// =======================================

	public long insert(String description) {
	    ContentValues newValues = new ContentValues();
	    newValues.put(DESCRIPTION_KEY, description);
	    return db.insert(DB_ANSWERS_TABLE, null, newValues);
	}

	public boolean update(AnswerModel answer) {
	    long id = answer.getId();
	    String description = answer.getDescription();
	    Long pictureId = answer.getPictureId();
	    return update(id, description, pictureId);
	}

	public boolean update(long id, String description,long pictureId) {
	    String where = ID_KEY + "=" + id;
	    ContentValues updateTodoValues = new ContentValues();
	    updateTodoValues.put(DESCRIPTION_KEY, description);
	    updateTodoValues.put(PICTURE_ID_KEY, pictureId);
	    return db.update(DB_ANSWERS_TABLE, updateTodoValues, where, null) > 0;
	}

	public boolean delete(long id){
	    String where = ID_KEY + "=" + id;
	    return db.delete(DB_ANSWERS_TABLE, where, null) > 0;
	}

	public Cursor getAll() {
	    String[] columns = {ID_KEY, DESCRIPTION_KEY, PICTURE_ID_KEY, LANG_VERSION_KEY};
	    return db.query(DB_ANSWERS_TABLE, columns, null, null, null, null, null);
	}

	public AnswerModel get(long id) {
	    String[] columns = {ID_KEY, DESCRIPTION_KEY, PICTURE_ID_KEY, LANG_VERSION_KEY};
	    String where = ID_KEY + "=" + id;
	    Cursor cursor = db.query(DB_ANSWERS_TABLE, columns, where, null, null, null, null);
	    AnswerModel model = null;
	    if(cursor != null && cursor.moveToFirst()) {
	        String description = cursor.getString(DESCRIPTION_COLUMN);
	        Long pictureId = cursor.getLong(PICTURE_ID_COLUMN);
	        String langVersion = cursor.getString(LANG_VERSION_COLUMN);
	        model = new AnswerModel(id, description, pictureId, langVersion);
	    }
	    return model;
	}

	public PictureModel getRandomPicture(List<Long> pictureIdShowed) throws NoPictureNextException {

		String queryAll = "SELECT " + IMAGE_ID_KEY + " FROM " + DB_IMAGE_TABLE;
		Cursor cursor = db.rawQuery(queryAll, null);
		//keep here all id pictures
		List<Long> ids = new ArrayList<Long>();
		if (cursor.moveToFirst()) {
			do {
				ids.add(cursor.getLong(0));
			} while (cursor.moveToNext());
		}
		cursor.close();

		ids.removeAll(pictureIdShowed);

		if(ids.size() <= 0) {
			throw new NoPictureNextException();
		}

		Random random = new Random();
		int randomPosition = random.nextInt(ids.size());
		Long randomId = ids.get(randomPosition);

		String[] columns = {IMAGE_ID_KEY, IMAGE_PICTURE_KEY};
		String where = IMAGE_ID_KEY + "= ?";
		String[] args = {randomId.toString()};
		Cursor cursorImage = db.query(DB_IMAGE_TABLE, columns, where, args, null, null, null);
		PictureModel model = null;
		if(cursorImage != null && cursorImage.moveToFirst()) {
			Long id = cursorImage.getLong(IMAGE_ID_COLUMN);
			byte[] blob = cursorImage.getBlob(IMAGE_PICTURE_COLUMN);
	        model = new PictureModel(id, blob);
	    }
		cursorImage.close();

		return model;
	}

	public AnswerModel[] get4AnswersForPicture(Long pictureId, String langVersion) throws ObjectNotFoundException {
		String method = "get4AnswersForPicture";
		Log.d(method, "Generate answer for picture id: >>> " + pictureId + ", for: " + langVersion);

		AnswerModel[] returnAnswers = new AnswerModel[4];

		//select all ID questions for selected lang version
		String queryMax = "SELECT " + ID_KEY + " FROM " + DB_ANSWERS_TABLE + " WHERE " + LANG_VERSION_KEY + " = '" + langVersion + "'";
		Cursor cursor = db.rawQuery(queryMax, null);
		//keept here all id questions in selecte lang version
		List<Long> ids = new ArrayList<Long>();
		if (cursor.moveToFirst()) {
			do {
				ids.add(cursor.getLong(0));
			} while (cursor.moveToNext());
		}
		cursor.close();

		String[] columns = {ID_KEY, DESCRIPTION_KEY, PICTURE_ID_KEY, LANG_VERSION_KEY};
		String where = PICTURE_ID_KEY + "= ? AND " + LANG_VERSION_KEY + "= ?";
		String[] args = {pictureId.toString(), langVersion};
		Cursor cursorPictureId = db.query(DB_ANSWERS_TABLE, columns, where, args, null, null, null);
	    AnswerModel modelGoodAnswer = getModel(cursorPictureId);

		if (modelGoodAnswer != null) {
			modelGoodAnswer.setGoodAnswer(true);
			ids.remove(modelGoodAnswer.getId());
			Random random = new Random();

			int count = 0;
			boolean goodAnswerSelected = false;
			while (true) {

				if (count >= 4)
					break;

				// tu wrzucamy w random
				if (!goodAnswerSelected) {
					int goodRandom = random.nextInt(8);
					Log.d(method, "Generate random answer for good answer: " + goodRandom + " < 3, count: " + count);
					if (goodRandom < 2 || count == 3) {
						returnAnswers[count++] = modelGoodAnswer;
						goodAnswerSelected = true;
						continue;
					}
				}

				//get random value from 0 to table size
				int generated = random.nextInt(ids.size());
				Long randomAnswerId = ids.get(generated);
				Log.d(method, "Generate random answer id: " + randomAnswerId);
				ids.remove(generated);

				String condition = ID_KEY + "=" + randomAnswerId;
				Cursor cursorId = db.query(DB_ANSWERS_TABLE, columns, condition, null, null, null, null);
				AnswerModel ans = getModel(cursorId);
				if (ans != null) {
					returnAnswers[count++] = ans;
				}
			}
		} else {
			throw new ObjectNotFoundException("No define answer for picture id: " + pictureId);
		}

		return returnAnswers;
	}

	private AnswerModel getModel(Cursor cursor) {
		AnswerModel model = null;
		if(cursor != null && cursor.moveToFirst()) {
			Long id = cursor.getLong(ID_COLUMN);
	        String description = cursor.getString(DESCRIPTION_COLUMN);
	        Long pictureId = cursor.getLong(PICTURE_ID_COLUMN);
	        String langVersion = cursor.getString(LANG_VERSION_COLUMN);
	        model = new AnswerModel(id, description, pictureId, langVersion);
	    }
		cursor.close();
		return model;
	}


	// =======================================

	public static class DatabaseHelper extends SQLiteOpenHelper {

		Resources res = null;

	    public DatabaseHelper(Context context, String name, CursorFactory factory, int version, Resources applicationResources) {
	        super(context, name, factory, version);
	        res = applicationResources;
	    }

	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        db.execSQL(DB_CREATE_ANSWERS_TABLE);
	        db.execSQL(DB_CREATE_IMAGE_TABLE);

	        Log.d(DEBUG_TAG, "Database creating...");
	        Log.d(DEBUG_TAG, "Table " + DROP_ANSWERS_TABLE + " ver." + DB_VERSION + " created");
	        Log.d(DEBUG_TAG, "Table " + DROP_IMAGE_TABLE + " ver." + DB_VERSION + " created");

	        insertDefaultValues(db);
	        insertDefaultImages(db);
	    }

	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        db.execSQL(DROP_ANSWERS_TABLE);
	        db.execSQL(DROP_IMAGE_TABLE);

	        Log.d(DEBUG_TAG, "Database updating...");
	        Log.d(DEBUG_TAG, "Table " + DROP_ANSWERS_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
	        Log.d(DEBUG_TAG, "Table " + DROP_IMAGE_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
	        Log.d(DEBUG_TAG, "All data is lost.");

	        onCreate(db);
	    }

	    private void insertDefaultImages(SQLiteDatabase db) {
	    	ContentValues newValues = new ContentValues();

	    	int compress = 10;
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.auto);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.babeczka);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.balon);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.banan);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.brat);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.burak);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.chlopiec);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.chmura);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.choinka);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.ciastko);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.corka);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.cukierek);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.dom);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.drzewo);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.dziewczynka);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.gruszka);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.jablko);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.kot);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
		    	Bitmap image = BitmapFactory.decodeResource(res, R.drawable.kwiatek);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.lody);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.mama);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.marchewka);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.mis);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.motyl);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.parasol);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.prezent);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.pszczola);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.ptak);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.rodzina);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.ryba);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.samolot);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.serce);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.siostra);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.slon);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.slonce);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.swieczka);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.syn);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.tata);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.truskawka);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    	{
	    		ByteArrayOutputStream blob = new ByteArrayOutputStream();
	    		Bitmap image = BitmapFactory.decodeResource(res, R.drawable.zyrafa);
		    	image.compress(CompressFormat.JPEG, compress, blob);
		    	newValues.put(IMAGE_PICTURE_KEY, blob.toByteArray());
		    	db.insert(DB_IMAGE_TABLE, null, newValues);
		    	Log.d("insertDefaultImages", "Size: " + blob.toByteArray().length);
	    	}
	    }

	    private void insertDefaultValues(SQLiteDatabase db) {
	    	ContentValues newValues = new ContentValues();
	    	// PL
	    	newValues.put(LANG_VERSION_KEY, StaticHelper.LANG_VERSION_PL);
	    	newValues.put(DESCRIPTION_KEY, "Samochód");
		    newValues.put(PICTURE_ID_KEY, 1L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
	    	
		    newValues.put(DESCRIPTION_KEY, "Babeczka");
		    newValues.put(PICTURE_ID_KEY, 2L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Balon");
		    newValues.put(PICTURE_ID_KEY, 3L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Banan");
		    newValues.put(PICTURE_ID_KEY, 4L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Brat");
		    newValues.put(PICTURE_ID_KEY, 5L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Burak");
		    newValues.put(PICTURE_ID_KEY, 6L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Chłopiec");
		    newValues.put(PICTURE_ID_KEY, 7L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Chmura");
		    newValues.put(PICTURE_ID_KEY, 8L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Choinka");
		    newValues.put(PICTURE_ID_KEY, 9L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Ciastko");
		    newValues.put(PICTURE_ID_KEY, 10L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Córka");
		    newValues.put(PICTURE_ID_KEY, 11L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Cukierek");
		    newValues.put(PICTURE_ID_KEY, 12L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Dom");
		    newValues.put(PICTURE_ID_KEY, 13L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Drzewo");
		    newValues.put(PICTURE_ID_KEY, 14L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Dziewczynka");
		    newValues.put(PICTURE_ID_KEY, 15L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Gruszka");
		    newValues.put(PICTURE_ID_KEY, 16L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Jabłko");
		    newValues.put(PICTURE_ID_KEY, 17L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Kot");
		    newValues.put(PICTURE_ID_KEY, 18L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Kwiatek");
		    newValues.put(PICTURE_ID_KEY, 19L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Lody");
		    newValues.put(PICTURE_ID_KEY, 20L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Mama");
		    newValues.put(PICTURE_ID_KEY, 21L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Marchewka");
		    newValues.put(PICTURE_ID_KEY, 22L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Miś");
		    newValues.put(PICTURE_ID_KEY, 23L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Motyl");
		    newValues.put(PICTURE_ID_KEY, 24L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Parasol");
		    newValues.put(PICTURE_ID_KEY, 25L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Prezent");
		    newValues.put(PICTURE_ID_KEY, 26L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Pszczoła");
		    newValues.put(PICTURE_ID_KEY, 27L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Ptak");
		    newValues.put(PICTURE_ID_KEY, 28L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Rodzina");
		    newValues.put(PICTURE_ID_KEY, 29L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Ryba");
		    newValues.put(PICTURE_ID_KEY, 30L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Samolot");
		    newValues.put(PICTURE_ID_KEY, 31L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Serce");
		    newValues.put(PICTURE_ID_KEY, 32L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Siostra");
		    newValues.put(PICTURE_ID_KEY, 33L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Słoń");
		    newValues.put(PICTURE_ID_KEY, 34L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Słońce");
		    newValues.put(PICTURE_ID_KEY, 35L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Świeczka");
		    newValues.put(PICTURE_ID_KEY, 36L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Syn");
		    newValues.put(PICTURE_ID_KEY, 37L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Tata");
		    newValues.put(PICTURE_ID_KEY, 38L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Truskawka");
		    newValues.put(PICTURE_ID_KEY, 39L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Żyrafa");
		    newValues.put(PICTURE_ID_KEY, 40L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    // EN
		    newValues.put(LANG_VERSION_KEY, StaticHelper.LANG_VERSION_EN);
	    	newValues.put(DESCRIPTION_KEY, "Car");
		    newValues.put(PICTURE_ID_KEY, 1L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
	    	
		    newValues.put(DESCRIPTION_KEY, "Cupcake");
		    newValues.put(PICTURE_ID_KEY, 2L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Baloon");
		    newValues.put(PICTURE_ID_KEY, 3L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Banana");
		    newValues.put(PICTURE_ID_KEY, 4L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Brother");
		    newValues.put(PICTURE_ID_KEY, 5L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Beetroot");
		    newValues.put(PICTURE_ID_KEY, 6L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Boy");
		    newValues.put(PICTURE_ID_KEY, 7L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Cloud");
		    newValues.put(PICTURE_ID_KEY, 8L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Christmas Tree");
		    newValues.put(PICTURE_ID_KEY, 9L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Cake");
		    newValues.put(PICTURE_ID_KEY, 10L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Doughter");
		    newValues.put(PICTURE_ID_KEY, 11L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Candy");
		    newValues.put(PICTURE_ID_KEY, 12L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "House");
		    newValues.put(PICTURE_ID_KEY, 13L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Tree");
		    newValues.put(PICTURE_ID_KEY, 14L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Girl");
		    newValues.put(PICTURE_ID_KEY, 15L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Pear");
		    newValues.put(PICTURE_ID_KEY, 16L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Apple");
		    newValues.put(PICTURE_ID_KEY, 17L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Cat");
		    newValues.put(PICTURE_ID_KEY, 18L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Flower");
		    newValues.put(PICTURE_ID_KEY, 19L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Ice Cream");
		    newValues.put(PICTURE_ID_KEY, 20L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Mother");
		    newValues.put(PICTURE_ID_KEY, 21L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Carrot");
		    newValues.put(PICTURE_ID_KEY, 22L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Teddy Bear");
		    newValues.put(PICTURE_ID_KEY, 23L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Butterfly");
		    newValues.put(PICTURE_ID_KEY, 24L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Umbrella");
		    newValues.put(PICTURE_ID_KEY, 25L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Gift");
		    newValues.put(PICTURE_ID_KEY, 26L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Bee");
		    newValues.put(PICTURE_ID_KEY, 27L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Bird");
		    newValues.put(PICTURE_ID_KEY, 28L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Family");
		    newValues.put(PICTURE_ID_KEY, 29L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Fish");
		    newValues.put(PICTURE_ID_KEY, 30L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Airplane");
		    newValues.put(PICTURE_ID_KEY, 31L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Heart");
		    newValues.put(PICTURE_ID_KEY, 32L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Sister");
		    newValues.put(PICTURE_ID_KEY, 33L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Elephant");
		    newValues.put(PICTURE_ID_KEY, 34L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Sun");
		    newValues.put(PICTURE_ID_KEY, 35L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Candle");
		    newValues.put(PICTURE_ID_KEY, 36L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Son");
		    newValues.put(PICTURE_ID_KEY, 37L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Father");
		    newValues.put(PICTURE_ID_KEY, 38L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Strawberry");
		    newValues.put(PICTURE_ID_KEY, 39L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Giraffe");
		    newValues.put(PICTURE_ID_KEY, 40L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);


		    //FR
		    newValues.put(LANG_VERSION_KEY, StaticHelper.LANG_VERSION_FR);
	    	newValues.put(DESCRIPTION_KEY, "Auto");
		    newValues.put(PICTURE_ID_KEY, 1L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
	    	
		    newValues.put(DESCRIPTION_KEY, "Pate");
		    newValues.put(PICTURE_ID_KEY, 2L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Ballon");
		    newValues.put(PICTURE_ID_KEY, 3L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Banane");
		    newValues.put(PICTURE_ID_KEY, 4L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Frere");
		    newValues.put(PICTURE_ID_KEY, 5L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Betterave");
		    newValues.put(PICTURE_ID_KEY, 6L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);

		    newValues.put(DESCRIPTION_KEY, "Garcon");
		    newValues.put(PICTURE_ID_KEY, 7L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Nuage");
		    newValues.put(PICTURE_ID_KEY, 8L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Sapin De Noel");
		    newValues.put(PICTURE_ID_KEY, 9L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Gateu");
		    newValues.put(PICTURE_ID_KEY, 10L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Fille");
		    newValues.put(PICTURE_ID_KEY, 11L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Bonbon");
		    newValues.put(PICTURE_ID_KEY, 12L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Maison");
		    newValues.put(PICTURE_ID_KEY, 13L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Arbre");
		    newValues.put(PICTURE_ID_KEY, 14L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Gosse");
		    newValues.put(PICTURE_ID_KEY, 15L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Poire");
		    newValues.put(PICTURE_ID_KEY, 16L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Pomme");
		    newValues.put(PICTURE_ID_KEY, 17L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Chat");
		    newValues.put(PICTURE_ID_KEY, 18L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Fleur");
		    newValues.put(PICTURE_ID_KEY, 19L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Glace");
		    newValues.put(PICTURE_ID_KEY, 20L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Mere");
		    newValues.put(PICTURE_ID_KEY, 21L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Carotte");
		    newValues.put(PICTURE_ID_KEY, 22L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Ourson");
		    newValues.put(PICTURE_ID_KEY, 23L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Papillon");
		    newValues.put(PICTURE_ID_KEY, 24L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Parapluie");
		    newValues.put(PICTURE_ID_KEY, 25L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Cadeau");
		    newValues.put(PICTURE_ID_KEY, 26L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Abeille");
		    newValues.put(PICTURE_ID_KEY, 27L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Oiseau");
		    newValues.put(PICTURE_ID_KEY, 28L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Famille");
		    newValues.put(PICTURE_ID_KEY, 29L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Poisson");
		    newValues.put(PICTURE_ID_KEY, 30L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Avion");
		    newValues.put(PICTURE_ID_KEY, 31L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Coeur");
		    newValues.put(PICTURE_ID_KEY, 32L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Soeur");
		    newValues.put(PICTURE_ID_KEY, 33L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Elephant");
		    newValues.put(PICTURE_ID_KEY, 34L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Soleil");
		    newValues.put(PICTURE_ID_KEY, 35L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Chandelle");
		    newValues.put(PICTURE_ID_KEY, 36L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Fils");
		    newValues.put(PICTURE_ID_KEY, 37L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Pere");
		    newValues.put(PICTURE_ID_KEY, 38L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Fraise");
		    newValues.put(PICTURE_ID_KEY, 39L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);
		    
		    newValues.put(DESCRIPTION_KEY, "Girafe");
		    newValues.put(PICTURE_ID_KEY, 40L);
		    db.insert(DB_ANSWERS_TABLE, null, newValues);


	    }
	}

}
