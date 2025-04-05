package model.entity;

import java.security.MessageDigest; //SHA-1やSHA-256などのメッセージ・ダイジェスト・アルゴリズムの機能を提供
import java.security.NoSuchAlgorithmException; //暗号アルゴリズムが要求されたにもかかわらず、現在の環境では使用不可能の場合にスロー
import java.util.HexFormat; //バイトと文字、１６進数でエンコードされた文字列の間で変換する

public class HashPassword {

	public String toHash(String text) throws NoSuchAlgorithmException {
		// SHA-256
		String hexString = null;
		try {
			//MessageDigest = 任意のサイズのデータを固定長のハッシュ値に変換する機能
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			//byte[] = 要素がbyte型の配列
			//getBytesメソッド = 対象の文字列をバイトシーケンスにエンコード化して結果をByte型の配列として返すメソッド
			//.digest指定されたデータからダイジェスト値を生成する
			byte[] sha256Byte = sha256.digest(text.getBytes());

			//ヘキサフォーマット　＝　バイトや整数と１６進数文字列を変換するクラスやファイル形式
			//.of() = 配列の要素を指定して新しい配列を作成する
			//.withLowerCase() = 小文字の１６進文字を使用するためにHexFormatのコピーを返す
			HexFormat hex = HexFormat.of().withLowerCase();
			//.formatHex = バイト配列からフォーマットされた１６進文字列を返す
			hexString = hex.formatHex(sha256Byte);
			System.out.println("SHA256");
			System.out.println(hexString);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexString;

	}

}
